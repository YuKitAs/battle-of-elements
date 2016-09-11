package xigua.battle.of.elements.logic.battle.magic;

import xigua.battle.of.elements.model.battle.magic.Element;
import xigua.battle.of.elements.model.battle.magic.Magic;

import java.util.ArrayList;
import java.util.List;

public class MagicFactory {
    public Magic buildMagicFromElementList(List<Element> elementList) {
        if (elementList.size() < 3) {
            return Magic.EMPTY;
        }

        Element element = getElement(elementList);
        List<Magic.Effect> effects = getMagicEffects(elementList);

        if (element == null || effects.stream().filter(effect -> effect.getLevel() > 0).count() == 0) {
            return Magic.EMPTY;
        }

        return new Magic(element, effects);
    }

    private Element getElement(List<Element> elementList) {
        return elementList.get(1);
    }

    private List<Magic.Effect> getMagicEffects(List<Element> elementList) {
        Element firstElement = elementList.get(0);
        List<Element> restElements = elementList.subList(2, elementList.size());
        List<Magic.Effect> effects = new ArrayList<>();

        Magic.Effect effect1;
        Magic.Effect effect2;

        switch (firstElement) {
            case FIRE:
                effect1 = Magic.Effect.DAMAGE;
                effect1.setLevel(countElement(Element.FIRE, restElements));
                effect2 = Magic.Effect.DEBUFF;
                effect2.setLevel(countElement(Element.WOOD, restElements));
                break;
            case WATER:
                effect1 = Magic.Effect.BLOCK;
                effect1.setLevel(countElement(Element.WATER, restElements));
                effect2 = Magic.Effect.BUFF;
                effect2.setLevel(countElement(Element.FIRE, restElements));
                break;
            case WOOD:
                effect1 = Magic.Effect.CLEANSE;
                effect1.setLevel(countElement(Element.WOOD, restElements));
                effect2 = Magic.Effect.HEAL;
                effect2.setLevel(countElement(Element.WATER, restElements));
                break;
            default:
                throw new RuntimeException("Unrecognized magic category.");
        }

        effects.add(effect1);
        effects.add(effect2);

        return effects;
    }

    private int countElement(Element expectedElement, List<Element> elementList) {
        return (int) elementList.stream().filter(element -> element == expectedElement).count();
    }
}
