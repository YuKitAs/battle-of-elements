package xigua.battle.of.elements.model.battle;

public class State {
    public static final State EMPTY = new State(Type.BUFF, Element.NONE, 0);

    private final Type type;
    private final Element element;
    private final int level;

    public State(Type type, Element element, int level) {
        this.type = type;
        this.element = element;
        this.level = level;
    }

    public Element getElement() {
        return element;
    }

    public Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public enum Type {
        DEBUFF, BUFF
    }
}
