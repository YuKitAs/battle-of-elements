package xigua.battle.of.elements.logic.battle;

import org.junit.Test;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.IntWithMax;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Environment;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BattleHelperTest {
    @Test
    public void getElementUsage_WithCorrectElementUsage() {
        assertThat(BattleHelper.getElementUsage(Element.FIRE)).isEqualTo(ElementUsage.ATTACK);
        assertThat(BattleHelper.getElementUsage(Element.WATER)).isEqualTo(ElementUsage.DEFEND);
        assertThat(BattleHelper.getElementUsage(Element.WOOD)).isEqualTo(ElementUsage.HEAL);
        assertThat(BattleHelper.getElementUsage(Element.NONE)).isEqualTo(ElementUsage.NONE);
    }

    @Test
    public void notify_AllBattlersNotified() {
        BattlerObserverMock battlerObserverMock = new BattlerObserverMock();

        Set<Battler> battlers = new HashSet<>();
        battlers.add(new Battler("John Doe", true, true, 10, 10, 10, new IntWithMax(10, 20), new IntWithMax(10, 20),
                5, new FreeElementBank(5), new BattlerControllerMock(), battlerObserverMock));

        BattleField battleField = new BattleField(battlers, Environment.BALANCED);

        BattleHelper.notifyAllBattlers(battleField, new Event(EventType.BATTLE_ACTION_STARTED));
        BattleHelper.notifyAllBattlers(battleField, new Event(EventType.BATTLE_ACTION_ENDED));

        assertThat(battlerObserverMock.getEventReceived().get(0).getType()).isEqualTo(EventType.BATTLE_ACTION_STARTED);
        assertThat(battlerObserverMock.getEventReceived().get(1).getType()).isEqualTo(EventType.BATTLE_ACTION_ENDED);
    }
}
