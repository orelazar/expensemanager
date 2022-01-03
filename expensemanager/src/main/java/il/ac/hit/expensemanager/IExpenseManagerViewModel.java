package il.ac.hit.expensemanager;

import java.util.Date;

public interface IExpenseManagerViewModel {
    public void setView(IExpenseManagerView view);
    public void setModel(IExpenseManagerModel model);
    public void addExpense(Expense expense);
    public void addCategory(String category);
    public void getExpenses();
    public void getExpensesInRange(Date from, Date until);
    public void getCategories();
}
