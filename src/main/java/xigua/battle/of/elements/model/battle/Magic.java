package xigua.battle.of.elements.model.battle;

public class Magic {
    public static final Magic EMPTY = new Magic(Element.NONE, Element.NONE, 0, 0);

    private final Element effectElement;
    private final Element typeElement;
    private final int primaryLevel;
    private final int secondaryLevel;

    public Magic(Element effectElement, Element typeElement, int primaryLevel, int secondaryLevel) {
        this.effectElement = effectElement;
        this.typeElement = typeElement;
        this.primaryLevel = primaryLevel;
        this.secondaryLevel = secondaryLevel;
    }

    public Element getEffectElement() {
        return effectElement;
    }

    public Element getTypeElement() {
        return typeElement;
    }

    public int getPrimaryLevel() {
        return primaryLevel;
    }

    public int getSecondaryLevel() {
        return secondaryLevel;
    }
}
