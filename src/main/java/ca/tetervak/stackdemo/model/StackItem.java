package ca.tetervak.stackdemo.model;

public class StackItem {

    private final int count;
    private final String value;

    public int getCount() {
        return count;
    }

    public String getValue() {
        return value;
    }

    public StackItem(int count, String value) {
        this.count = count;
        this.value = value;
    }
}
