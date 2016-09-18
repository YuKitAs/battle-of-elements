package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.model.MessageBox;

public class ExitState implements GameState {
    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        return null;
    }
}
