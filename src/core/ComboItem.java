package core;

public class ComboItem {
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private int key;
    private String value;

    public ComboItem(int key,String value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
