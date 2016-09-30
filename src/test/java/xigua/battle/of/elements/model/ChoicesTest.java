package xigua.battle.of.elements.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChoicesTest {
    private Choices choices;
    private final List<String> choicesList = new ArrayList<>();

    @Before
    public void setUp() {
        choicesList.add("foo");
        choicesList.add("bar");

        choices = new Choices(ChoicePurpose.BATTLE_ACTION, choicesList);
    }

    @Test
    public void getPurpose() {
        assertThat(choices.getPurpose()).isEqualTo(ChoicePurpose.BATTLE_ACTION);
    }

    @Test
    public void getChoices() {
        assertThat(choices.getChoices()).containsExactly("foo", "bar");
    }

    @Test
    public void chooseAndGetChosenIndex_WithCorrectIndex() {
        choices.choose(0);

        assertThat(choices.getChosenIndex()).isEqualTo(0);
    }

    @Test
    public void chooseNonExistentIndex_ExceptionThrown() {
        assertThatThrownBy(() -> choices.choose(42)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Chosen" +
                " index must be within the range of 0 to " + choicesList.size());
    }

    @Test
    public void getChosenItem() {
        choices.choose(0);

        assertThat(choices.getChosenItem()).isEqualTo("foo");
    }
}