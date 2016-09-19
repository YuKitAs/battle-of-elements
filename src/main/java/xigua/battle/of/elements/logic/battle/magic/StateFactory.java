package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.State;

public class StateFactory {
    public State fromMagic(Magic magic) {
        return new State(magic.getEffectElement(), magic.getTypeElement(), magic.getSecondaryLevel());
    }
}
