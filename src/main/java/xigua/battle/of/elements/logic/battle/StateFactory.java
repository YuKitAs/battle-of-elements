package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.State;

public class StateFactory {
    public State fromMagic(Magic magic) {
        if (magic.getType() != Magic.Type.ATTACK && magic.getType() != Magic.Type.DEFEND) {
            return State.EMPTY;
        }

        return new State(magic.getType() == Magic.Type.ATTACK ? State.Type.DEBUFF : State.Type.BUFF, magic.getElement
                (), magic.getSecondaryLevel());
    }
}
