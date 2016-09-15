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

        Element effectElement = summonedElementBank.getFirst();

        return new Magic(effectElement, summonedElementBank.getSecond(), summonedElementBank.getElementLevelCount
                (effectElement), summonedElementBank.getElementLevelCount(effectElement.getDestructedElement()));
    }

    public Magic buildNonTypedMagic(Element effectElement, int primaryLevel) {
        return new Magic(effectElement, Element.NONE, primaryLevel, 0);
    }
}
