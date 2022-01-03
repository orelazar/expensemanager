package il.ac.hit.expensemanager;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ExpenseManagerModel implements IExpenseManagerModel {

    final String EXPENSE_TBL = "expenses_tbl";
    final String CATEGORY_TBL = "categories_tbl";


    private List<String> categories;
    private List<Expense> expenses;
    private static int key = 0;

    String driverFullQualifiedName = "com.mysql.jdbc.Driver";
    String connectionString = "jdbc:mysql://localhost:8889/or_nadav";

    public ExpenseManagerModel() throws ExpenseManagerException{
        try{
            Class.forName(driverFullQualifiedName);
        }catch(ClassNotFoundException e){
            throw new ExpenseManagerException("couldn't register mysql driver to driver the driver manager", e);
        }
    }

    @Override
    public void addCategory(String category) throws ExpenseManagerException {
        String query = "insert into "+CATEGORY_TBL+" (id,category)"+
                "values(?,?)";
        Connection connection =null;
        try {
            connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,category);
            if((preparedStatement.executeUpdate()) != 1){
                throw new ExpenseManagerException("unable to add Category to db");
            }
        }catch(SQLException e){
            throw new ExpenseManagerException("something went wrong trying to connect to db", e);
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addExpense(Expense expense) throws ExpenseManagerException{
        String query = "insert into "+EXPENSE_TBL+"(id,sum,currency,category,description,date)"+
                "values(?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = connect();
            java.sql.Date date = new java.sql.Date(expense.getDate().getTime());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,0);
            preparedStatement.setDouble(2,expense.getSum());
            preparedStatement.setString(3,expense.getCurrency());
            preparedStatement.setString(4,expense.getCategory());
            preparedStatement.setString(5,expense.getDescription());
            preparedStatement.setDate(6,date);
            if(preparedStatement.executeUpdate()!= 1){
                throw new ExpenseManagerException("unable to add expense to db");
            }
        }catch(SQLException e){
            throw new ExpenseManagerException("something went wrong trying to connect to db", e);
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Expense> getReport() throws ExpenseManagerException{
        Connection connection = null;
        try{
            connection = connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `"+EXPENSE_TBL+"`");
            expenses = new LinkedList<>();
            while(rs.next())
            {
                double sum = rs.getDouble("sum");
                String currency = rs.getString("currency");
                String category = rs.getString("category");
                String desc = rs.getString("description");
                Date date = new Date(rs.getDate("date").getTime());
                expenses.add(new Expense(sum,currency,category,desc,date));
            }
            return expenses;
        }
        catch (SQLException e){
            throw new ExpenseManagerException("something went wrong trying to connect to db", e);
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Expense> getReport(Date from, Date until) throws ExpenseManagerException{
        Connection connection = null;
        try{
            connection = connect();
            Statement statement = connection.createStatement();
            java.sql.Date date_from = new java.sql.Date(from.getTime());
            java.sql.Date date_until = new java.sql.Date(until.getTime());
            ResultSet rs = statement.executeQuery("SELECT * FROM `"+EXPENSE_TBL+"` WHERE date BETWEEN '" +
                    date_from +"'" + " AND '" + date_until+ "'");
            expenses = new LinkedList<>();
            while(rs.next())
            {
                double sum = rs.getDouble("sum");
                String currency = rs.getString("currency");
                String category = rs.getString("category");
                String desc = rs.getString("description");
                Date date = new Date(rs.getDate("date").getTime());
                expenses.add(new Expense(sum,currency,category,desc,date));
            }
            connection.close();
            return expenses;
        }
        catch (SQLException e){
            throw new ExpenseManagerException("something went wrong trying to connect to db", e);
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public List<String> getCategories() throws ExpenseManagerException{
        Connection connection = null;
        try{
            connection = connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `"+CATEGORY_TBL+"`");
            categories = new LinkedList<>();
            while(rs.next())
            {
                String category;
                category = rs.getString("category");
                categories.add(category);
            }
            connection.close();
            return categories;
        }
        catch (SQLException e){
            throw new ExpenseManagerException("something went wrong trying to connect to db", e);
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection connect() throws SQLException{
            return DriverManager.getConnection(connectionString, "or_nadav", "ornadav");
    }
}
