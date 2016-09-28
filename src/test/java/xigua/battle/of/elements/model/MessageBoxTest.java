package xigua.battle.of.elements.model;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.logic.statemachine.GameStateMock1;
import xigua.battle.of.elements.logic.statemachine.GameStateMock2;

import static org.assertj.core.api.Assertions.assertThat;


public class MessageBoxTest {
    private static final String MESSAGE_TITLE = "Mew Message";
    private static final String MESSAGE_BODY = "Mew mew mew!";
    private final Message message = new Message(GameStateMock1.class, GameStateMock2.class, MESSAGE_TITLE,
            MESSAGE_BODY);
    private MessageBox messageBox;

    @Before
    public void setUp() {
        messageBox = new MessageBox();
    }

    @Test
    public void sendAndPeek_WithCorrectMessage() {
        messageBox.sendMessage(message);

        assertThat(messageBox.peekMessage(message.getReceiver())).isEqualTo(message);
    }

    @Test
    public void poll_MessageRemoved() {
        messageBox.sendMessage(message);

        assertThat(messageBox.pollMessage(message.getReceiver())).isEqualTo(message);
        assertThat(messageBox.peekMessage(message.getReceiver())).isNull();
    }
}