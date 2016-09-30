package xigua.battle.of.elements.model.battle.battler;

import org.junit.Test;
import xigua.battle.of.elements.logic.battle.BattlerControllerMock;
import xigua.battle.of.elements.logic.battle.BattlerObserverMock;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.utility.DeepCopy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BattlerTest {
    @Test
    public void buildWithEmptyFields_ExceptionThrown() {
        assertThatThrownBy(() -> {
            new Battler.Builder().withAttack(3).build();
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void deepCopy_DifferentObjectWithSameContent() {
        Battler battler = new Battler.Builder().withName("Foo").isFriendly(true).isMagician(true).withAttack(10)
                .withDefence(10).withFreeElementBank(new FreeElementBank(10)).withSummonedElementBankSize(6)
                .withHitPoint(100, 100).withSpeed(10).withController(new BattlerControllerMock()).withObserver(new
                        BattlerObserverMock()).build();

        Battler copiedBattler = DeepCopy.copy(battler);

        assertThat(copiedBattler == battler).isFalse();
        assertThat(copiedBattler).isEqualToComparingFieldByFieldRecursively(battler);
    }
}