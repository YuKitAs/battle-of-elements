package xigua.battle.of.elements.logic;

import java.util.HashMap;
import java.util.Map;

public class GameStateFactory {
    private final Map<Class<? extends GameState>, GameState> instantiatedStates = new HashMap<>();

    public GameState buildGameState(Class<? extends GameState> gameStateClass) {
        if (!instantiatedStates.containsKey(gameStateClass)) {
            try {
                instantiatedStates.put(gameStateClass, gameStateClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Error while creating new state.", e);
            }
        }

        return instantiatedStates.get(gameStateClass);
    }
}
