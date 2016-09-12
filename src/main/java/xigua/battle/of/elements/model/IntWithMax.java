package xigua.battle.of.elements.model;

public class IntWithMax {
    private int max;
    private int value;

    public IntWithMax(int max) {
        this(max, max);
    }

    public IntWithMax(int value, int max) {
        this.value = value;
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
