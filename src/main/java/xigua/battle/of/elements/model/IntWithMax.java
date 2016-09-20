package xigua.battle.of.elements.model;

import static java.lang.Math.min;

public class IntWithMax {
    private final int max;
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
        this.value = min(value, max);
    }

    public int getMax() {
        return max;
    }
}
