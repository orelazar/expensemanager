package il.ac.hit.expensemanager;

import javax.swing.*;

public class Test {

    public static void main(String args[]) throws ExpenseManagerException {

        //IModel model = new SimpleInMemoryToDoListModel();

        IExpenseManagerView view = new ExpenseManagerView();
        IExpenseManagerViewModel vm = new ExpenseManagerViewModel();
        IExpenseManagerModel model = new ExpenseManagerModel();
        vm.setView(view);
        vm.setModel(model);
        view.setIViewModel(vm);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });
    }
}
