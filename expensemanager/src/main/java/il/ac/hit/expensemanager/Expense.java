package il.ac.hit.expensemanager;

import java.util.Date;
import java.util.Objects;

public class Expense{


    private double sum;
    private String currency;
    private String category;
    private String description;
    private Date date;



    public Expense(double sum, String currency, String category, String description, Date date) {
        this.setSum(sum);
        this.setCurrency(currency);
        this.setCategory(category);
        this.setDescription(description);
        this.setDate(date);
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        if(sum > 0) {
            this.sum = sum;
        }
        else{
            sum = 0;
        }
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        if(currency != null) {
            this.currency = currency;
        }
        else{
            this.currency = "USD";
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(category !=null) {
            this.category = category;
        }
        else{
            this.category = "General";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description!=null) {
            this.description = description;
        }
        else{
            description = "";
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if(date!=null) {
            this.date = new Date(date.getTime());
        }
        else{
            this.date = new Date();
        }
    }

    @Override
    public String toString() {
        String str = "sum: " +getSum() +"  category: " + getCategory()+ "  date: " + getDate()+"  currency: "+ getCurrency();
        return str;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, currency, category, description, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Expense)) {
            return false;
        }
        Expense e = (Expense)obj;
        if (this.getDate().equals(e.getDate())&& this.getCurrency().equals(e.getCurrency() )&&
                this.getCategory().equals(e.getCategory() )&& this.getSum() == e.getSum() &&
                this.getDescription().equals(e.getDescription())) {
            return true;
        }
        return false;
    }
}
