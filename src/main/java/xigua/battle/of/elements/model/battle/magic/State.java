package xigua.battle.of.elements.model.battle;

public class State {
    public static final State EMPTY = new State(Element.NONE, Element.NONE, 0);

    private final Element effectElement;
    private final Element typeElement;
    private final int level;

    public State(Element effectElement, Element typeElement, int level) {
        this.effectElement = effectElement;
        this.typeElement = typeElement;
        this.level = level;
    }

    public Element getEffectElement() {
        return effectElement;
    }

    public Element getTypeElement() {
        return typeElement;
    }

    public int getLevel() {
        return level;
    }

    public enum Type {
        DEBUFF, BUFF
    }
}
