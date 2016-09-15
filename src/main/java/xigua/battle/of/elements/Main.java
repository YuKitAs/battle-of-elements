package xigua.battle.of.elements;

import xigua.battle.of.elements.logic.GameStateMachine;
import xigua.battle.of.elements.logic.WorldState;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        new GameStateMachine().start(WorldState.class);
    }
}
