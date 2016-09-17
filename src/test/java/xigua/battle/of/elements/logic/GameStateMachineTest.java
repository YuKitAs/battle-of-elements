package xigua.battle.of.elements.logic;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.Message;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStateMachineTest {

    private static final String MESSAGE_TITLE = "Mew Message";
    private static final String MESSAGE_BODY = "Mew mew mew!";

    private GameStateFactory gameStateFactory;
    private GameStateMachine gameStateMachine;

    @Before
    public void setUp() {
        gameStateFactory = new GameStateFactory();
        gameStateMachine = new GameStateMachine(gameStateFactory);
    }

    @Test
    public void start_StateCalledWithCorrectOrder() {
        gameStateMachine.start(GameStateMock1.class);

        GameStateMock1 gameStateMock1 = (GameStateMock1) gameStateFactory.buildGameState(GameStateMock1.class);
        GameStateMock2 gameStateMock2 = (GameStateMock2) gameStateFactory.buildGameState(GameStateMock2.class);

        assertThat(gameStateMock1.getLastStateRecieved()).isNotEmpty().containsExactly(null, gameStateMock2);
        assertThat(gameStateMock2.getLastStateRecieved()).isNotEmpty().containsExactly(gameStateMock1, gameStateMock1);
    }

    @Test
    public void start_StateRecieveCorrectMessage() {
        GameStateMock1 gameStateMock1 = (GameStateMock1) gameStateFactory.buildGameState(GameStateMock1.class);
        GameStateMock2 gameStateMock2 = (GameStateMock2) gameStateFactory.buildGameState(GameStateMock2.class);

        gameStateMock1.setMessage(MESSAGE_TITLE, MESSAGE_BODY);

        gameStateMachine.start(GameStateMock1.class);

        Message expectedMessage1 = new Message(GameStateMock2.class, GameStateMock1.class, MESSAGE_TITLE, MESSAGE_BODY);
        Message expectedMessage2 = new Message(GameStateMock1.class, GameStateMock2.class, MESSAGE_TITLE, MESSAGE_BODY);

        assertThat(gameStateMock1.getMessageReceived()).isNotEmpty().containsExactly(null, expectedMessage1);
        assertThat(gameStateMock2.getMessageReceived()).isNotEmpty().containsExactly(expectedMessage2,
                expectedMessage2);
    }
}