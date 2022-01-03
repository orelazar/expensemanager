package il.ac.hit.guicomponnents;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

public class FilterByDateDialog extends JDialog{

    private JButton btnOk;
    private JButton btnCancel;

    private JSpinner spinnerDayFrom;
    private JSpinner spinnerMonthFrom;
    private JSpinner spinnerYearFrom;

    private JSpinner spinnerDayUntil;
    private JSpinner spinnerMonthUntil;
    private JSpinner spinnerYearUntil;

    public FilterByDateDialog(Frame parentFrame){
        Point loc = parentFrame.getLocation();
        setLocation(loc.x+80,loc.y+80);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SpinnerModel spinnerMonthModelFrom = new SpinnerNumberModel(calendar.get(Calendar.MONTH)+1,1,12,1);
        SpinnerModel spinnerDayModelFrom = new SpinnerNumberModel(calendar.get(Calendar.DATE),1,31,1);
        SpinnerModel spinnerYearModelFrom = new SpinnerNumberModel(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.YEAR) -10,
                calendar.get(Calendar.YEAR) +10,1);
        SpinnerModel spinnerMonthModelUntil = new SpinnerNumberModel(calendar.get(Calendar.MONTH)+1,1,12,1);
        SpinnerModel spinnerDayModelUntil = new SpinnerNumberModel(calendar.get(Calendar.DATE),1,31,1);
        SpinnerModel spinnerYearModelUntil = new SpinnerNumberModel(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.YEAR) -10,
                calendar.get(Calendar.YEAR) +10,1);
        spinnerDayFrom = new JSpinner(spinnerDayModelFrom);
        spinnerMonthFrom = new JSpinner(spinnerMonthModelFrom);
        spinnerYearFrom = new JSpinner(spinnerYearModelFrom);
        spinnerDayUntil = new JSpinner(spinnerDayModelUntil);
        spinnerMonthUntil = new JSpinner(spinnerMonthModelUntil);
        spinnerYearUntil = new JSpinner(spinnerYearModelUntil);
        JLabel labelDayFrom = new JLabel("day");
        JLabel labelMonthFrom = new JLabel("Month");
        JLabel labelYearFrom = new JLabel("Year");
        JLabel labelDayUntil = new JLabel("day");
        JLabel labelMonthUntil = new JLabel("Month");
        JLabel labelYearUntil = new JLabel("Year");
        JLabel labelFrom = new JLabel("FROM:");

        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(labelFrom,gbc);
        gbc.gridx =1;
        panel.add(labelMonthFrom,gbc);
        gbc.gridx = 2;
        panel.add(spinnerMonthFrom,gbc);
        gbc.gridx =3;
        panel.add(labelDayFrom,gbc);
        gbc.gridx = 4;
        panel.add(spinnerDayFrom,gbc);
        gbc.gridx = 5;
        panel.add(labelYearFrom,gbc);
        gbc.gridx = 6;
        panel.add(spinnerYearFrom,gbc);

        JLabel spacer = new JLabel(" ");
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(spacer,gbc);

        JLabel labelUntil = new JLabel("UNTIL:");
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(labelUntil,gbc);
        gbc.gridx = 1;
        panel.add(labelMonthUntil,gbc);
        gbc.gridx = 2;
        panel.add(spinnerMonthUntil,gbc);
        gbc.gridx =3;
        panel.add(labelDayUntil,gbc);
        gbc.gridx = 4;
        panel.add(spinnerDayUntil,gbc);
        gbc.gridx = 5;
        panel.add(labelYearUntil,gbc);
        gbc.gridx = 6;
        panel.add(spinnerYearUntil,gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(spacer,gbc);

        btnOk = new JButton("OK");
        gbc.gridwidth = 1;
        gbc.gridy =4;
        gbc.gridx = 0;
        panel.add(btnOk,gbc);

        btnCancel = new JButton("Cancel");
        gbc.gridx = 1;
        panel.add(btnCancel,gbc);

        getContentPane().add(panel);
        pack();

    }

    public void run(){
        this.setVisible(true);
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JSpinner getSpinnerDayFrom() {
        return spinnerDayFrom;
    }

    public JSpinner getSpinnerMonthFrom() {
        return spinnerMonthFrom;
    }

    public JSpinner getSpinnerYearFrom() {
        return spinnerYearFrom;
    }

    public JSpinner getSpinnerDayUntil() {
        return spinnerDayUntil;
    }

    public JSpinner getSpinnerMonthUntil() {
        return spinnerMonthUntil;
    }

    public JSpinner getSpinnerYearUntil() {
        return spinnerYearUntil;
    }
}
