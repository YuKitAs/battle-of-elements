package xigua.battle.of.elements.model.battle;

import java.util.ArrayList;
import java.util.List;

public enum Environment {
    GRASSLAND(1, 1, 1), TOWN(1, 1, 1), LAKE(2, 3, 2), FOREST(2, 2, 3);

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
