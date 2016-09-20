package xigua.battle.of.elements.model.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentTest {
    @Test
    public void getElementProportions_WithCorrectProportions() {
        assertThat(Environment.BALANCED.getElementProportions()).containsExactly(Element.FIRE, Element.WATER, Element
                .WOOD);
        assertThat(Environment.MORE_FIRE.getElementProportions()).containsExactly(Element.FIRE, Element.FIRE, Element
                .FIRE, Element.WATER, Element.WATER, Element.WOOD, Element.WOOD);
        assertThat(Environment.FIRE_DOMINANT.getElementProportions()).containsExactly(Element.FIRE, Element.FIRE,
                Element.WATER, Element.WOOD);
    }
}