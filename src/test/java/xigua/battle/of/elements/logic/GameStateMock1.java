package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.Message;
import xigua.battle.of.elements.model.MessageBox;

public class GameStateMock1 extends AbstractGameStateMock {
    private String title;
    private String message;

    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        lastStateRecieved.add(lastState);

        messageBox.sendMessage(new Message(this.getClass(), GameStateMock2.class, title, message));
        messageReceived.add(messageBox.pollMessage(this.getClass()));

        return GameStateMock2.class;
    }

    public void setMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
