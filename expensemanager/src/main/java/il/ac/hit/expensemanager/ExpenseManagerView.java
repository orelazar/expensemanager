package il.ac.hit.expensemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import il.ac.hit.guicomponnents.AddExpenseDialog;
import il.ac.hit.guicomponnents.FilterByDateDialog;

public class ExpenseManagerView  implements IExpenseManagerView{

    private IExpenseManagerViewModel vm;
    private Collection<String> categories;

    private JFrame frame;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelBottom;
    private JPanel panelTop;

    private JButton btnAddExpense;
    private JButton btnAddCategory;
    private JButton btnFilter;

    private JTextField txtFilter;
    private JSpinner spinnerFilterBy;

    private TextArea fullDescription;
    private JList<Expense> expenseJList;
    //private JScrollPane listScroller;
    private DefaultListModel<Expense> defaultListModel;


    @Override
    public void init() {
        frame = new JFrame("ExMen");
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelTop = new JPanel();
        panelBottom = new JPanel();

        btnAddExpense = new JButton("add expense");
        btnAddCategory = new JButton("add category");
        btnFilter = new JButton("filter");
        //txtFilter = new JTextField("mm/DD/yyyy - mm/DD/yyyy",20);

        List<String> filterStrings = new LinkedList<>();
        filterStrings.add("dates");
        filterStrings.add("categories");
        SpinnerListModel spinnerModel = new SpinnerListModel(filterStrings);
        spinnerFilterBy = new JSpinner(spinnerModel);
        JComponent editor = spinnerFilterBy.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setColumns(7);

       // listScroller = new JScrollPane();
        expenseJList = new JList<>();
        defaultListModel = new DefaultListModel<>();
        expenseJList.setModel(defaultListModel);

        fullDescription = new TextArea("full expense info");
        fullDescription.setFont(new Font("Serif", Font.BOLD, 15));

    }

    public void createComponents(){
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(50);
        borderLayout.setVgap(50);
        frame.setLayout(borderLayout);
        frame.setBounds(100,100,70,70);

        FlowLayout flowLayoutTop = new FlowLayout();
        flowLayoutTop.setHgap(10);
        panelTop.setLayout(flowLayoutTop);
        panelTop.add(spinnerFilterBy);
        //panelTop.add(txtFilter);
        panelTop.add(btnFilter);

        FlowLayout flowLayoutBottom = new FlowLayout();
        flowLayoutBottom.setHgap(15);
        panelBottom.setLayout(flowLayoutBottom);
        panelBottom.add(btnAddExpense);
        panelBottom.add(btnAddCategory);

        expenseJList.setFont(new Font("Serif", Font.BOLD, 15));
        expenseJList.setLayoutOrientation(JList.VERTICAL);
        //listScroller.setViewportView(expenseJList);
        panelLeft.setLayout(new BorderLayout());
        panelLeft.add(expenseJList);
        panelLeft.setBackground(Color.DARK_GRAY);

        panelRight.setLayout(new BorderLayout());
        panelRight.setBackground(Color.DARK_GRAY);
        panelRight.add(fullDescription);


        frame.add(panelTop,BorderLayout.NORTH);
        frame.add(panelBottom,BorderLayout.SOUTH);
        frame.add(panelRight,BorderLayout.EAST);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.setSize(1200,600);
        frame.setVisible(true);

    }

    @Override
    public void start() {
        createComponents();
        vm.getCategories();
        vm.getExpenses();
        btnAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategoryDialogHandler();
            }
        });
        btnAddExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpenseDialogHandler();
            }
        });
        expenseJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fullDescription.setText(expenseJList.getModel().getElementAt(expenseJList.getSelectedIndex()).getDescription());
            }
        });
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(spinnerFilterBy.getValue().equals("dates")) {
                    filterByDateDialogHandler();
                }
            }
        });
    }


    @Override
    public void showErrorMessage(ErrorMessage message) {
        JOptionPane.showMessageDialog(frame,
                message.getText(),
                "error",
                JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void showExpenses(Collection<Expense> expenses) {
        try {
            defaultListModel.removeAllElements();
            defaultListModel.addAll(expenses);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }



    @Override
    public void updateCategories(Collection<String> categories) {
        this.categories = categories;
    }


    @Override
    public void setIViewModel(IExpenseManagerViewModel vm) {
        this.vm = vm;
    }

    public void filterByDateDialogHandler(){

        FilterByDateDialog filterDialog = new FilterByDateDialog(frame);
        filterDialog.run();

        filterDialog.getBtnOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = (int)filterDialog.getSpinnerDayFrom().getValue();
                int month = (int)filterDialog.getSpinnerMonthFrom().getValue();
                int year = (int)filterDialog.getSpinnerYearFrom().getValue();
                Date dateFrom = new GregorianCalendar(year, month-1, day).getTime();
                day = (int)filterDialog.getSpinnerDayUntil().getValue();
                month = (int)filterDialog.getSpinnerMonthUntil().getValue();
                year = (int)filterDialog.getSpinnerYearUntil().getValue();
                Date dateUntil = new GregorianCalendar(year, month-1, day).getTime();
                vm.getExpensesInRange(dateFrom,dateUntil);
                filterDialog.dispose();
            }
        });
        filterDialog.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterDialog.dispose();
            }
        });


    }

    public void addCategoryDialogHandler(){
        String s = JOptionPane.showInputDialog(
                frame,
                "Enter Category",
                "add category",
                JOptionPane.PLAIN_MESSAGE);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            vm.addCategory(s);
        }
    }

    public void addExpenseDialogHandler(){
        AddExpenseDialog expenseDialog = new AddExpenseDialog(frame, categories.toArray(new String[0]));
        expenseDialog.run();
        expenseDialog.getBtnOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double sum = Double.parseDouble(expenseDialog.getTxtSumInput().getText());
                    String category = (String)expenseDialog.getCategoryList().getSelectedItem();
                    String currency = (String)expenseDialog.getCurrencyList().getSelectedItem();
                    String desc = expenseDialog.getTxtAreaDesc().getText();
                    int day = (int)expenseDialog.getSpinnerDay().getValue();
                    int month = (int)expenseDialog.getSpinnerMonth().getValue();
                    int year = (int)expenseDialog.getSpinnerYear().getValue();
                    Date date = new GregorianCalendar(year, month-1, day).getTime();
                    Expense expense = new Expense(sum,currency,category,desc,date);
                    vm.addExpense(expense);
                    expenseDialog.dispose();

                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(expenseDialog,
                            "Please Enter A Valid Sum",
                            "error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        expenseDialog.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expenseDialog.dispose();
            }
        });
    }
}