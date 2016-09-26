package xigua.battle.of.elements.logic.battle.actions;

import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CommonRulesTest {
    @Test
    public void getElementUsage_WithCorrectElementUsage() {
        assertThat(CommonRules.getElementUsage(Element.FIRE)).isEqualTo(ElementUsage.ATTACK);
        assertThat(CommonRules.getElementUsage(Element.WATER)).isEqualTo(ElementUsage.DEFEND);
        assertThat(CommonRules.getElementUsage(Element.WOOD)).isEqualTo(ElementUsage.HEAL);
        assertThat(CommonRules.getElementUsage(Element.NONE)).isEqualTo(ElementUsage.NONE);
    }
}
