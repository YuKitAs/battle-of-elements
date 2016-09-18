package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.MessageBox;

public interface GameState {
    Class<? extends GameState> run(GameState lastState, MessageBox messageBox);
}
