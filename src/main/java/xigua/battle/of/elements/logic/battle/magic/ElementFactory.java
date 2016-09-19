package xigua.battle.of.elements.logic.battle.magic;

import xigua.battle.of.elements.model.battle.magic.Element;
import xigua.battle.of.elements.model.battle.magic.Environment;

import java.util.List;
import java.util.Random;

public class ElementFactory {
    private final List<Element> elementProportions;
    private final Random random = new Random();

    public ElementFactory(Environment environment) {
        elementProportions = environment.getElementProportions();
    }

    public ElementFactory(Environment environment, long randomSeed) {
        this(environment);
        random.setSeed(randomSeed);
    }

    public Element getElement() {
        return elementProportions.get(random.nextInt(elementProportions.size()));
    }
}
