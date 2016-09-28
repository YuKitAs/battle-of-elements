package xigua.battle.of.elements.model;

import org.junit.Test;
import xigua.battle.of.elements.logic.statemachine.GameStateMock1;
import xigua.battle.of.elements.logic.statemachine.GameStateMock2;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {
    private static final String MESSAGE_TITLE = "Mew Message";
    private static final String MESSAGE_BODY = "Mew mew mew!";
    private final Message message = new Message(GameStateMock1.class, GameStateMock2.class, MESSAGE_TITLE,
            MESSAGE_BODY);

    @Test
    public void getSender_WithCorrectSender() {
        assertThat(message.getSender()).isEqualTo(GameStateMock1.class);
    }

    @Test
    public void getReceiver_WithCorrectReceiver() {
        assertThat(message.getReceiver()).isEqualTo(GameStateMock2.class);
    }

    @Test
    public void getTitle_WithCorrectTitle() {
        assertThat(message.getTitle()).isEqualTo(MESSAGE_TITLE);
    }

    @Test
    public void getContent_WithCorrectContent() {
        assertThat(message.getContent()).isEqualTo(MESSAGE_BODY);
    }
}