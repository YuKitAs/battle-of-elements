package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.logic.statemachine.GameState;
import xigua.battle.of.elements.model.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameStateMock implements GameState {
    protected final List<GameState> lastStateRecieved = new ArrayList<>();
    protected final List<Message> messageReceived = new ArrayList<>();

    public List<GameState> getLastStateRecieved() {
        return lastStateRecieved;
    }

    public List<Message> getMessageReceived() {
        return messageReceived;
    }
}
