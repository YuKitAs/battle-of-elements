package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

public class MagicFactory {
    public Magic buildFromSummonedElementBank(SummonedElementBank summonedElementBank) {
        // Magic must have at least 4 elements:
        //   1. magic type;
        //   2. main element;
        //   3. magic level;
        //   4. end of the magic.
        if (summonedElementBank.getCurrentSize() < 4) {
            return Magic.EMPTY;
        }

        Element typeElement = summonedElementBank.getFirst();

        return new Magic(getMagicTypeFromElement(typeElement), summonedElementBank.getSecond(), summonedElementBank
                .getElementLevelCount(typeElement), summonedElementBank.getElementLevelCount(typeElement
                .getDestructedElement()));
    }

    public Magic buildPhysicalAttack(int level) {
        return new Magic(Magic.Type.ATTACK, Element.NONE, level, 0);
    }

    public Magic buildPhysicalDefence(int level) {
        return new Magic(Magic.Type.DEFEND, Element.NONE, level, 0);
    }

    private Magic.Type getMagicTypeFromElement(Element element) {
        switch (element) {
            case FIRE:
                return Magic.Type.ATTACK;
            case WATER:
                return Magic.Type.DEFEND;
            case WOOD:
                return Magic.Type.RECOVER;
            default:
                throw new RuntimeException("Unknown element for magic type.");
        }
    }
}
