package xigua.battle.of.elements.model.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementTest {
    @Test
    public void getDestructedElement_WithCorrectElement() {
        assertThat(Element.NONE.getDestructedElement()).isEqualTo(Element.NONE);
        assertThat(Element.FIRE.getDestructedElement()).isEqualTo(Element.WOOD);
        assertThat(Element.WATER.getDestructedElement()).isEqualTo(Element.FIRE);
        assertThat(Element.WOOD.getDestructedElement()).isEqualTo(Element.WATER);
    }

    @Test
    public void getConstructedElement_WithCorrectElement() {
        assertThat(Element.NONE.getConstructedElement()).isEqualTo(Element.NONE);
        assertThat(Element.FIRE.getConstructedElement()).isEqualTo(Element.WATER);
        assertThat(Element.WATER.getConstructedElement()).isEqualTo(Element.WOOD);
        assertThat(Element.WOOD.getConstructedElement()).isEqualTo(Element.FIRE);
    }
}