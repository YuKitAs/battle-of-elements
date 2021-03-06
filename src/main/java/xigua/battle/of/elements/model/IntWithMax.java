package xigua.battle.of.elements.model;

import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IntWithMax implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int maxValue;
    private int value;

    public IntWithMax(int maxValue) {
        this(maxValue, maxValue);
    }

    public IntWithMax(int value, int maxValue) {
        this.value = value;
        this.maxValue = maxValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = max(0, min(value, maxValue));
    }

    public int getMaxValue() {
        return maxValue;
    }
}
