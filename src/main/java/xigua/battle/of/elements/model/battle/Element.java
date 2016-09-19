package xigua.battle.of.elements.model.battle;

public enum Element {
    FIRE, WATER, WOOD, NONE;

    public Element getDestructedElement() {
        switch (this) {
            case NONE:
                return NONE;
            case FIRE:
                return WOOD;
            case WATER:
                return FIRE;
            case WOOD:
                return WATER;
            default:
                throw new RuntimeException("No such element.");
        }
    }

    public Element getConstructedElement() {
        switch (this) {
            case NONE:
                return NONE;
            case FIRE:
                return WATER;
            case WATER:
                return WOOD;
            case WOOD:
                return FIRE;
            default:
                throw new RuntimeException("No such element.");
        }
    }
}
