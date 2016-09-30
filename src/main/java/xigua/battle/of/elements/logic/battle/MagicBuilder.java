package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

public final class MagicBuilder {
    public static Magic buildEmptyMagic() {
        return new Magic(ElementUsage.NONE, Element.NONE, 0, 0);
    }

    public static Magic buildFromSummonedElementBank(SummonedElementBank summonedElementBank) {
        if (!verifyElementBank(summonedElementBank)) {
            return buildEmptyMagic();
        }

        Element usageElement = summonedElementBank.getFirst();
        Element typeElement = summonedElementBank.getSecond();

        return new Magic(getElementUsage(usageElement), typeElement, summonedElementBank.getElementLevelCount(typeElement),
                summonedElementBank.getElementLevelCount(typeElement.getDestructedElement()));
    }

    public static Magic buildDebuffState(Element type, int debuffLevel) {
        return new Magic(ElementUsage.ATTACK, type, 0, debuffLevel);
    }

    public static Magic buildPhysicalAttack(int attackLevel) {
        return new Magic(ElementUsage.ATTACK, Element.NONE, attackLevel, 0);
    }

    public static boolean canBuildMagic(SummonedElementBank summonedElementBank) {
        if (summonedElementBank.getCurrentSize() < 3) {
            return false;
        }

        if (summonedElementBank.getFirst() == Element.NONE) {
            return false;
        }

        Element typeElement = summonedElementBank.getSecond();
        Element endElement = summonedElementBank.getLast();

        return endElement.getDestructedElement() == typeElement;
    }

    private static boolean verifyElementBank(SummonedElementBank summonedElementBank) {
        if (!canBuildMagic(summonedElementBank)) {
            throw new RuntimeException("Cannot build magic from this bank, have you checked it before?");
        }

        if (summonedElementBank.getCurrentSize() < 4) {
            return false;
        }

        Element primaryElement = summonedElementBank.getSecond();
        Element secondaryElement = primaryElement.getDestructedElement();
        int currentSize = summonedElementBank.getCurrentSize();

        return summonedElementBank.getElementLevelCount(primaryElement) + summonedElementBank.getElementLevelCount(secondaryElement) + 3
                == currentSize;
    }

    private static ElementUsage getElementUsage(Element element) {
        switch (element) {
            case FIRE:
                return ElementUsage.ATTACK;
            case WATER:
                return ElementUsage.DEFEND;
            case WOOD:
                return ElementUsage.HEAL;
            default:
                return ElementUsage.NONE;
        }
    }
}
