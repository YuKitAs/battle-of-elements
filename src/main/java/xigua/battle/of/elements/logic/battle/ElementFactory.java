package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Environment {
    private final Map<Element, Integer> elementProportions;
    private final int totalProportion;

    public Environment(Type type) {
        elementProportions = type.getElementProportions();
        totalProportion = elementProportions.values().stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public Element getElement() {

    }

    public enum Type {
        GRASSLAND(1, 1, 1), TOWN(1, 1, 1), LAKE(2, 3, 2), FOREST(2, 2, 3);

        private final Map<Element, Integer> elementProportions = new HashMap<>();

        Type(int... proportions) {
            elementProportions.put(Element.FIRE, proportions[0]);
            elementProportions.put(Element.WATER, proportions[1]);
            elementProportions.put(Element.WOOD, proportions[2]);
        }

        private Map<Element, Integer> getElementProportions() {
            return Collections.unmodifiableMap(elementProportions);
        }
    }
}
