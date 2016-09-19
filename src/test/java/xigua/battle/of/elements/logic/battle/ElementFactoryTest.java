package xigua.battle.of.elements.logic.battle;

import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Environment;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementFactoryTest {
    private static final long SEED = 10000;

    @Test
    public void getElementInBalancedEnv_ReturnSameElementsWithSameSeed() {
        ElementFactory elementFactory = new ElementFactory(Environment.BALANCED, SEED);

        assertThat(elementFactory.getElement()).isEqualTo(Element.FIRE);
        assertThat(elementFactory.getElement()).isEqualTo(Element.WATER);
        assertThat(elementFactory.getElement()).isEqualTo(Element.WOOD);
        assertThat(elementFactory.getElement()).isEqualTo(Element.WATER);
        assertThat(elementFactory.getElement()).isEqualTo(Element.WOOD);
        assertThat(elementFactory.getElement()).isEqualTo(Element.WATER);
        assertThat(elementFactory.getElement()).isEqualTo(Element.FIRE);
    }
}