package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

public class MagicFactory {
    public Magic buildFromSummonedElementBank(SummonedElementBank summonedElementBank) {
        // Magic must have at least 4 elements:
        //   1. magic type: attack, defend, heal;
        //   2. main element: fire, water, wood;
        //   3. magic level: primary element (same as magic type) count, secondary element (destructed by magic type)
        //      count;
        //   4. end of the magic: destruct magic type.
        // eg. [FIRE, WATER, FIRE, FIRE, WOOD, WATER] is an attack magic with element WATER, primary level is 2,
        // secondary level is 1.
        verifyElementBank(summonedElementBank);

        if (summonedElementBank.getCurrentSize() < 4) {
            return Magic.EMPTY;
        }

        Element effectElement = summonedElementBank.getFirst();

        return new Magic(effectElement, summonedElementBank.getSecond(), summonedElementBank.getElementLevelCount
                (effectElement), summonedElementBank.getElementLevelCount(effectElement.getDestructedElement()));
    }

    public Magic buildNonTypedMagic(Element effectElement, int primaryLevel) {
        return new Magic(effectElement, Element.NONE, primaryLevel, 0);
    }

    private void verifyElementBank(SummonedElementBank summonedElementBank) {
        int currentSize = summonedElementBank.getCurrentSize();
        Element effectElement = summonedElementBank.getFirst();

        if (currentSize < 3) {
            throw new RuntimeException("Too few elements for casting magic.");
        }

        if (summonedElementBank.getLast().getDestructedElement() != effectElement) {
            throw new RuntimeException("Element bank is not ended properly.");
        }

        if (summonedElementBank.getElementLevelCount(effectElement) + summonedElementBank.getElementLevelCount(
                (effectElement.getDestructedElement())) + 3 != currentSize) {
            throw new RuntimeException("Invalid element bank for casting magic.");
        }
    }
}
