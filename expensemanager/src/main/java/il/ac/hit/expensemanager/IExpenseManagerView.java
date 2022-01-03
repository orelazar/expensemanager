package il.ac.hit.expensemanager;

import java.util.Collection;

public interface IExpenseManagerView {
    public void init();
    public void start();
    public void setIViewModel(IExpenseManagerViewModel vm);
    public void showExpenses(Collection<Expense> expenses);
    public void updateCategories(Collection<String> categories);
    public void showErrorMessage(ErrorMessage message);
}
