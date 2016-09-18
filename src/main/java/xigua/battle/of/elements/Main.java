package xigua.battle.of.elements;

import xigua.battle.of.elements.logic.statemachine.GameStateFactory;
import xigua.battle.of.elements.logic.statemachine.GameStateMachine;
import xigua.battle.of.elements.logic.statemachine.WorldState;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        new GameStateMachine(new GameStateFactory()).start(WorldState.class);
    }
}
