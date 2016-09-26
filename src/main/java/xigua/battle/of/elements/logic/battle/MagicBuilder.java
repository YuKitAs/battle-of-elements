package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.SummonedElementBank;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;

public class MagicBuilder {
    public Magic fromSummonedElementBank(SummonedElementBank summonedElementBank) {
        // Magic must have at least 4 elements:
        //   1. magic type: attack, defend, heal;
        //   2. main element: fire, water, wood;
        //   3. magic level: primary element (same as magic type) count, secondary element (destructed by magic type)
        //      count;
        //   4. end of the magic: destruct magic type.
        // eg. [FIRE, WATER, FIRE, FIRE, WOOD, WATER] is an attack magic with element WATER, primary level is 2,
        // secondary level is 1.
        if (!verifyElementBank(summonedElementBank)) {
            return Magic.EMPTY;
        }

        Element usageElement = summonedElementBank.getFirst();
        Element typeElement = summonedElementBank.getSecond();

        return new Magic(BattleHelper.getElementUsage(usageElement), typeElement, summonedElementBank
                .getElementLevelCount(usageElement), summonedElementBank.getElementLevelCount(usageElement
                .getDestructedElement()));
    }

    public Magic physicalAttackMagic(int primaryLevel) {
        return new Magic(ElementUsage.ATTACK, Element.NONE, primaryLevel, 0);
    }

    private boolean verifyElementBank(SummonedElementBank summonedElementBank) {
        int currentSize = summonedElementBank.getCurrentSize();
        Element usageElement = summonedElementBank.getFirst();

        if (currentSize < 4) {
            return false;
        }

        if (summonedElementBank.getLast().getDestructedElement() != usageElement) {
            return false;
        }

        if (summonedElementBank.getElementLevelCount(usageElement) + summonedElementBank.getElementLevelCount(
                (usageElement.getDestructedElement())) + 3 != currentSize) {
            return false;
        }

        return true;
    }
}
