package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

public final class MagicBuilder {
    public static Magic buildFromSummonedElementBank(SummonedElementBank summonedElementBank) {
        if (!verifyElementBank(summonedElementBank)) {
            return Magic.EMPTY;
        }

        Element usageElement = summonedElementBank.getFirst();
        Element typeElement = summonedElementBank.getSecond();

        return new Magic(BattleHelper.getElementUsage(usageElement), typeElement, summonedElementBank
                .getElementLevelCount(usageElement), summonedElementBank.getElementLevelCount(usageElement
                .getDestructedElement()));
    }

    public static Magic buildDebuffState(Element type, int debuffLevel) {
        return new Magic(ElementUsage.ATTACK, type, 0, debuffLevel);
    }

    public static Magic buildPhysicalAttack(int attackLevel) {
        return new Magic(ElementUsage.ATTACK, Element.NONE, attackLevel, 0);
    }

    private static boolean verifyElementBank(SummonedElementBank summonedElementBank) {
        int currentSize = summonedElementBank.getCurrentSize();
        Element usageElement = summonedElementBank.getFirst();

        if (currentSize < 4) {
            return false;
        }

        if (summonedElementBank.getLast().getDestructedElement() != usageElement) {
            return false;
        }

        return summonedElementBank.getElementLevelCount(usageElement) + summonedElementBank.getElementLevelCount(
                (usageElement.getDestructedElement())) + 3 == currentSize;
    }
}
