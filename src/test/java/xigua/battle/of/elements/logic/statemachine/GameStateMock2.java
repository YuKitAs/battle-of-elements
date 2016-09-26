package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.model.Message;
import xigua.battle.of.elements.model.MessageBox;

public class GameStateMock2 extends AbstractGameStateMock {
    private boolean called = false;

    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        lastStateRecieved.add(lastState);

        Message receivedMessage = messageBox.pollMessage(this.getClass());
        Message mewMessage = new Message(this.getClass(), GameStateMock1.class, receivedMessage.getTitle(),
                receivedMessage.getContent());
        messageBox.sendMessage(mewMessage);
        messageReceived.add(receivedMessage);

        if (lastStateRecieved.size() == 2) {
            return ExitState.class;
        }

        return GameStateMock1.class;
    }
}
