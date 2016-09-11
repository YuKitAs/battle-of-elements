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
}
