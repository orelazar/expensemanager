package il.ac.hit.expensemanager;

import java.util.Date;
import java.util.List;

public interface IExpenseManagerModel {
    void addCategory(String category) throws ExpenseManagerException;
    List<String> getCategories() throws ExpenseManagerException;
    void addExpense(Expense expense) throws ExpenseManagerException;
    List<Expense> getReport() throws ExpenseManagerException;
    List<Expense> getReport(Date from, Date until) throws ExpenseManagerException;
}
