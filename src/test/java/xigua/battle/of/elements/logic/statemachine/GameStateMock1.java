package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.model.Message;
import xigua.battle.of.elements.model.MessageBox;

import java.io.Serializable;

public class GameStateMock1 extends AbstractGameStateMock {
    private String title;
    private String content;

    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        lastStateRecieved.add(lastState);

        messageBox.sendMessage(new Message(this.getClass(), GameStateMock2.class, title, content));
        messageReceived.add(messageBox.pollMessage(this.getClass()));

        return GameStateMock2.class;
    }

    public void setMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
