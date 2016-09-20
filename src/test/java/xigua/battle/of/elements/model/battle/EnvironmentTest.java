package xigua.battle.of.elements.model.battle;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentTest {
    @Test
    public void getElementProportions_WithCorrectProportions() {
        List<Element> balancedElementProp = Arrays.asList(Element.FIRE, Element.WATER, Element.WOOD);
        List<Element> moreFireElementProp = Arrays.asList(Element.FIRE, Element.FIRE, Element.FIRE, Element.WATER,
                Element.WATER, Element.WOOD, Element.WOOD);
        List<Element> fireDominantElementProp = Arrays.asList(Element.FIRE, Element.FIRE, Element.WATER, Element.WOOD);

        assertThat(Environment.BALANCED.getElementProportions()).isEqualTo(balancedElementProp);
        assertThat(Environment.MORE_FIRE.getElementProportions()).isEqualTo(moreFireElementProp);
        assertThat(Environment.FIRE_DOMINANT.getElementProportions()).isEqualTo(fireDominantElementProp);
    }
}