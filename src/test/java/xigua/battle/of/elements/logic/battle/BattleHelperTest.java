package xigua.battle.of.elements.logic.battle;

import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BattleHelperTest {
    @Test
    public void getElementUsage_WithCorrectElementUsage() {
        assertThat(BattleHelper.getElementUsage(Element.FIRE)).isEqualTo(ElementUsage.ATTACK);
        assertThat(BattleHelper.getElementUsage(Element.WATER)).isEqualTo(ElementUsage.DEFEND);
        assertThat(BattleHelper.getElementUsage(Element.WOOD)).isEqualTo(ElementUsage.HEAL);
        assertThat(BattleHelper.getElementUsage(Element.NONE)).isEqualTo(ElementUsage.NONE);
    }
}
