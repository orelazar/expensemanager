package il.ac.hit.guicomponnents;


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AddExpenseDialog extends JDialog {

    private JTextField txtSumInput;
    private JSpinner spinnerDay;
    private JSpinner spinnerMonth;
    private JSpinner spinnerYear;
    private JComboBox<String> categoryList;
    private JComboBox<String> currencyList;
    private JTextArea txtAreaDesc;

    private JButton btnOk;
    private JButton btnCancel;



    public AddExpenseDialog(Frame parentFrame, String[] categories){

        Point loc = parentFrame.getLocation();
        setLocation(loc.x+80,loc.y+80);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        JLabel sumLabel = new JLabel("Sum:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(sumLabel,gbc);
        txtSumInput = new JTextField(10);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtSumInput,gbc);
        JLabel categoryLabel = new JLabel("Choose Category");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(categoryLabel,gbc);
        categoryList = new JComboBox<>(categories);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(categoryList,gbc);
        JLabel spacer = new JLabel(" ");
        JLabel labelCurrency = new JLabel("currency");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelCurrency, gbc);
        String[] currencyOptions = {"USD", "EUR" , "ILS" , "GBP"};
        currencyList = new JComboBox<>(currencyOptions);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(currencyList,gbc);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SpinnerModel spinnerMonthModel = new SpinnerNumberModel(calendar.get(Calendar.MONTH)+1,1,12,1);
        SpinnerModel spinnerDayModel = new SpinnerNumberModel(calendar.get(Calendar.DATE),1,31,1);
        SpinnerModel spinnerYearModel = new SpinnerNumberModel(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.YEAR) -10,
                calendar.get(Calendar.YEAR) +10,1);
        spinnerDay = new JSpinner(spinnerDayModel);
        spinnerMonth = new JSpinner(spinnerMonthModel);
        spinnerYear = new JSpinner(spinnerYearModel);
        JLabel labelDay = new JLabel("day");
        JLabel labelMonth = new JLabel("Month");
        JLabel labelYear = new JLabel("Year");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelMonth,gbc);
        gbc.gridx = 1;
        panel.add(spinnerMonth,gbc);
        gbc.gridx =2;
        panel.add(labelDay,gbc);
        gbc.gridx = 3;
        panel.add(spinnerDay,gbc);
        gbc.gridx = 4;
        panel.add(labelYear,gbc);
        gbc.gridx = 5;
        panel.add(spinnerYear,gbc);

        JLabel labelDesc = new JLabel("Description");
        txtAreaDesc = new JTextArea(3,20);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelDesc,gbc);
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(txtAreaDesc,gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(spacer,gbc);

        btnOk = new JButton("Ok");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(btnOk,gbc);
        btnCancel = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(btnCancel,gbc);
        getContentPane().add(panel);
        pack();
    }

    public JTextField getTxtSumInput() {
        return txtSumInput;
    }

    public JSpinner getSpinnerDay() {
        return spinnerDay;
    }

    public JSpinner getSpinnerMonth() {
        return spinnerMonth;
    }

    public JSpinner getSpinnerYear() {
        return spinnerYear;
    }

    public JComboBox<String> getCategoryList() {
        return categoryList;
    }

    public JComboBox<String> getCurrencyList() {
        return currencyList;
    }

    public JTextArea getTxtAreaDesc() {
        return txtAreaDesc;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }


    public void run(){
        this.setVisible(true);
    }
}
