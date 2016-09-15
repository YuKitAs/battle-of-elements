package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.MessageBox;

import java.util.HashMap;
import java.util.Map;

public class GameStateMachine {
    public void start(Class<? extends GameState> startStateClass) throws IllegalAccessException,
            InstantiationException {
        Map<Class<? extends GameState>, GameState> instantiatedStates = new HashMap<>();
        instantiatedStates.put(startStateClass, startStateClass.newInstance());

        MessageBox messageBox = new MessageBox();

        GameState lastState = null;
        GameState currentState = instantiatedStates.get(startStateClass);
        Class<? extends GameState> nextStateClass = null;

        while (!(currentState instanceof ExitState)) {
            nextStateClass = currentState.run(lastState, messageBox);

            if (!instantiatedStates.containsKey(nextStateClass)) {
                instantiatedStates.put(nextStateClass, nextStateClass.newInstance());
            }
            lastState = currentState;
            currentState = instantiatedStates.get(nextStateClass);
        }
    }
}
