package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.MessageBox;

public class BattleState implements GameState {
    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        System.out.println("Hello world!");

        return ExitState.class;
    }
}
