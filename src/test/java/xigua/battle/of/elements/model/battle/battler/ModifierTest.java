package xigua.battle.of.elements.model.battle.battler;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ModifierTest {
    private final Modifier modifier = new Modifier();

    @Test
    public void updateRestActions_WithCorrectResetModifierFactor() {
        modifier.setModifier(2, 1);
        modifier.updateRestActions();
        modifier.updateRestActions();

        assertThat(modifier.getModifierFactor()).isEqualTo(1);
    }

    @Test
    public void setAndGetModifierFactor_WithCorrectModifierFactor() {
        modifier.setModifier(2, 1);

        assertThat(modifier.getModifierFactor()).isEqualTo(2);
    }
}