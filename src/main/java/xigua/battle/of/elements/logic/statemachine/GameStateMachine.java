package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.MessageBox;

public class GameStateMachine {
    private final GameStateFactory gameStateFactory;
    private final MessageBox messageBox = new MessageBox();

    public GameStateMachine(GameStateFactory gameStateFactory) {
        this.gameStateFactory = gameStateFactory;
    }

    public void start(Class<? extends GameState> startStateClass) {
        GameState lastState = null;
        GameState currentState = gameStateFactory.buildGameState(startStateClass);
        Class<? extends GameState> nextStateClass;

        while (!(currentState instanceof ExitState)) {
            nextStateClass = currentState.run(lastState, messageBox);
            lastState = currentState;
            currentState = gameStateFactory.buildGameState(nextStateClass);
        }
    }
}
