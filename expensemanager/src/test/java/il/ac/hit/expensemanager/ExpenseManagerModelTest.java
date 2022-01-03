package il.ac.hit.expensemanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseManagerModelTest {

    private ExpenseManagerModel model;

    @BeforeEach
    void setUp() {
        try {
            model = new ExpenseManagerModel();
        }catch (ExpenseManagerException e){
            System.out.println("unable to create model object");
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Connection connection = model.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("truncate `expenses_tbl`");
            ResultSet rss = statement.executeQuery("truncate `categories_tbl`");
        }
        catch (SQLException e){

        }
        model = null;
    }

    @Test
    void addCategory() throws ExpenseManagerException{
        int size_expected = 1;
        List<String> categories_expected= new LinkedList<>();
        categories_expected.add("food");
        categories_expected.add("rent");
        categories_expected.add("bills");
        for(int i= 0 ; i<3;i++) {
            model.addCategory(categories_expected.get(i));
            assertNotNull(model.getCategories());
            int size_actual = model.getCategories().size();
            boolean category_actual = model.getCategories().contains(categories_expected.get(i));
            assertEquals(size_expected, size_actual, 0.0);
            assertTrue(category_actual);
            size_expected++;
        }
    }

    @Test
    void getCategories() throws ExpenseManagerException{
        model.addCategory("food");
        Assertions.assertDoesNotThrow(() ->model.getCategories());
        assertNotEquals(0,model.getCategories().size());
    }

    @Test
    void addExpense() throws ExpenseManagerException{
        int size_expected = 1;
        List<Expense> expenses_expected= new LinkedList<>();
        Calendar c = Calendar.getInstance();
        Date date;
        c.set(2000, Calendar.FEBRUARY, 2,0,0,0);
        date = c.getTime();
        expenses_expected.add(new Expense(100,"USD","food","expense",date));
        expenses_expected.add(new Expense(90,"ILS","rent","expense",date));
        expenses_expected.add(new Expense(80,"USD","food","expense",date));
        for(int i = 0 ; i<3;i++) {
            model.addExpense(expenses_expected.get(i));
            List<Expense> list;
            list = model.getReport();
            int size_actual = list.size();
            boolean category_actual = false;
            for(int index = 0; index<list.size();index++){
                if(list.get(index).equals(expenses_expected.get(i))){
                    category_actual = true;
                }
            }
            assertEquals(size_expected, size_actual, 0.0);
            assertTrue(category_actual);
            size_expected++;
        }
    }

    @Test
    void getReport() throws ExpenseManagerException{
        Date date = new Date();
        model.addExpense(new Expense(100,"USD","food","first",date));
        Assertions.assertDoesNotThrow(()-> model.getReport());
        assertNotEquals(0, model.getReport().size());
    }

    @Test
    void testGetReport() throws ExpenseManagerException {

        Calendar c = Calendar.getInstance();
        Date date;
        c.set(2000, Calendar.JANUARY, 1,0,0,0);
        date = c.getTime();
        model.addExpense(new Expense(100,"USD","food","first",date));
        c.set(2000, Calendar.FEBRUARY, 2,0,0,0);
        date = c.getTime();
        model.addExpense(new Expense(100,"USD","food","second",date));
        c.set(2000, Calendar.APRIL, 2,0,0,0);
        date = c.getTime();
        model.addExpense(new Expense(100,"USD","food","third",date));
        c.set(2001, Calendar.FEBRUARY, 2,0,0,0);
        date = c.getTime();
        model.addExpense(new Expense(100,"USD","food","forth",date));

        c.set(2000, Calendar.FEBRUARY, 1,0,0,0);
        Date date_from = c.getTime();
        c.set(2000, Calendar.MAY, 1,0,0,0);
        Date date_until = c.getTime();

        List<Expense> list = model.getReport(date_from,date_until);
        assertNotEquals(0,list.size());
        for(Expense expense: list) {
            assertTrue(expense.getDate().after(date_from));
            assertTrue(expense.getDate().before(date_until));
        }
    }
}

