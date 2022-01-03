package il.ac.hit.expensemanager;

public class ErrorMessage {

    private String content;

    public ErrorMessage(String content) {
        this.content = content;
    }

    public String getText() {
        return content;
    }
}
