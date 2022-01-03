package il.ac.hit.expensemanager;

public class ExpenseManagerException extends Exception{
    public ExpenseManagerException(String message) {
        super(message);
    }

    public ExpenseManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
