package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.MessageBox;

public class WorldState implements GameState {
    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        return BattleState.class;
    }
}