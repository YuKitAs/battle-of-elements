package xigua.battle.of.elements.model.battle;

import org.junit.Test;
import xigua.battle.of.elements.utility.DeepCopy;
import xigua.battle.of.elements.utility.DummySerializable;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleEventTest {
    @Test
    public void putAndGetAttribute_WithCorrectAttribute() {
        DummySerializable obj = new DummySerializable(0, 42);

        BattleEvent.BATTLE_START.putAttribute("foo", obj);

        assertThat(BattleEvent.BATTLE_START.getAttribute("foo")).isEqualTo(DeepCopy.toBytes(obj));
    }
}