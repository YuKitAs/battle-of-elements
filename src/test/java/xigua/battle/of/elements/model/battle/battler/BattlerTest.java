package xigua.battle.of.elements.model.battle.battler;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BattlerTest {
    @Test
    public void buildWithEmptyFields_ExceptionThrown() {
        assertThatThrownBy(() -> {
            new Battler.Builder().withAttack(3).build();
        }).isInstanceOf(NullPointerException.class);
    }
}