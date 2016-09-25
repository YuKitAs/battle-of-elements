package xigua.battle.of.elements.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Environment implements Serializable {
    BALANCED(1, 1, 1), MORE_FIRE(3, 2, 2), MORE_WATER(2, 3, 2), MORE_WOOD(2, 2, 3), FIRE_DOMINANT(2, 1, 1),
    WATER_DOMINANT(1, 2, 1), WOOD_DOMINANT(1, 1, 2);

    private static final long serialVersionUID = 1L;

    private final List<Element> elementProportions = new ArrayList<>();

    Environment(int... proportions) {
        for (int i = 0; i < proportions[0]; i++) {
            elementProportions.add(Element.FIRE);
        }

        for (int i = 0; i < proportions[1]; i++) {
            elementProportions.add(Element.WATER);
        }

        for (int i = 0; i < proportions[2]; i++) {
            elementProportions.add(Element.WOOD);
        }
    }

    public List<Element> getElementProportions() {
        return elementProportions;
    }
}
