package xigua.battle.of.elements.model.battle;

import java.io.Serializable;

public enum Element implements Serializable {
    FIRE, WATER, WOOD, NONE;

    private static final long serialVersionUID = 1L;

    public Element getDestructedElement() {
        switch (this) {
            case FIRE:
                return WOOD;
            case WATER:
                return FIRE;
            case WOOD:
                return WATER;
            default:
                return NONE;
        }
    }

    public Element getConstructedElement() {
        switch (this) {
            case FIRE:
                return WATER;
            case WATER:
                return WOOD;
            case WOOD:
                return FIRE;
            default:
                return NONE;
        }
    }
}
