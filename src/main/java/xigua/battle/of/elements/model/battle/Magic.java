package xigua.battle.of.elements.model.battle;

import java.io.Serializable;

public class Magic implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Magic EMPTY = new Magic(ElementUsage.NONE, Element.NONE, 0, 0);

    private final ElementUsage usage;
    private final Element type;
    private int primaryLevel;
    private int secondaryLevel;

    public Magic(ElementUsage usage, Element type, int primaryLevel, int secondaryLevel) {
        this.usage = usage;
        this.type = type;
        this.primaryLevel = primaryLevel;
        this.secondaryLevel = secondaryLevel;
    }

    public ElementUsage getUsage() {
        return usage;
    }

    public Element getType() {
        return type;
    }

    public void setPrimaryLevel(int primaryLevel) {
        this.primaryLevel = primaryLevel;
    }

    public void setSecondaryLevel(int secondaryLevel) {
        this.secondaryLevel = secondaryLevel;
    }

    public int getPrimaryLevel() {
        return primaryLevel;
    }

    public int getSecondaryLevel() {
        return secondaryLevel;
    }
}
