package xigua.battle.of.elements.logic.battle.magic;

import xigua.battle.of.elements.model.battle.magic.Magic;
import xigua.battle.of.elements.model.battle.magic.State;

public class StateFactory {
    public State fromMagic(Magic magic) {
        return new State(magic.getEffectElement(), magic.getTypeElement(), magic.getSecondaryLevel());
    }
}
