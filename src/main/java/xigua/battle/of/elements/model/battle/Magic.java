package xigua.battle.of.elements.model.battle;

public class Magic {
    public static final Magic EMPTY = new Magic(Type.RECOVER, Element.NONE, 0, 0);

    private final Type type;
    private final Element element;
    private final int primaryLevel;
    private final int secondaryLevel;

    public Magic(Type type, Element element, int primaryLevel, int secondaryLevel) {
        this.type = type;
        this.element = element;
        this.primaryLevel = primaryLevel;
        this.secondaryLevel = secondaryLevel;
    }

    public Type getType() {
        return type;
    }

    public Element getElement() {
        return element;
    }

    public int getPrimaryLevel() {
        return primaryLevel;
    }

    public int getSecondaryLevel() {
        return secondaryLevel;
    }

    public enum Type {
        ATTACK, DEFEND, RECOVER
    }
}
