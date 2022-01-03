package il.ac.hit.expensemanager;

import javax.swing.*;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExpenseManagerViewModel implements IExpenseManagerViewModel{

    private IExpenseManagerView view;
    private IExpenseManagerModel model;
    private ExecutorService service;



    public ExpenseManagerViewModel() {
        service = Executors.newFixedThreadPool(6);
    }

    @Override
    public void setView(IExpenseManagerView view) {
        this.view = view;
    }

    @Override
    public void setModel(IExpenseManagerModel model) {
        this.model = model;
    }

    @Override
    public void addExpense(Expense expense) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addExpense(expense);
                    Collection<Expense> expenses = model.getReport();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showExpenses(expenses);
                        }
                    });
                }
                catch (ExpenseManagerException e){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showErrorMessage(new ErrorMessage(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void addCategory(String category) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(category);
                    Collection<String> categories = model.getCategories();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.updateCategories(categories);
                        }
                    });
                }
                catch (ExpenseManagerException e){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showErrorMessage(new ErrorMessage(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getExpenses() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Expense> expenses = model.getReport();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showExpenses(expenses);
                        }
                    });
                }
                catch (ExpenseManagerException e){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showErrorMessage(new ErrorMessage(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getExpensesInRange(Date from, Date until) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Expense> expenses = model.getReport(from,until);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showExpenses(expenses);
                        }
                    });
                }
                catch (ExpenseManagerException e){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showErrorMessage(new ErrorMessage(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getCategories() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<String> categories = model.getCategories();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.updateCategories(categories);
                        }
                    });
                }
                catch (ExpenseManagerException e){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showErrorMessage(new ErrorMessage(e.getMessage()));
                        }
                    });
                }
            }
        });
    }
}
