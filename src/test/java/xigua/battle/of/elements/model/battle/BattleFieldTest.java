package xigua.battle.of.elements.model.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.logic.battle.BattlerControllerMock;
import xigua.battle.of.elements.logic.battle.BattlerObserverMock;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleFieldTest {
    private final Battler FRIENDLY_BATTLER = new Battler.Builder().withName("John").isFriendly(true).isMagician(true)
            .withAttack(10).withDefence(10).withSpeed(10).withHitPoint(10, 10).withSummonedElementBankSize(5)
            .withFreeElementBank(new FreeElementBank(5)).withController(new BattlerControllerMock()).withObserver(new
                    BattlerObserverMock()).build();

    private final Battler ENEMY_BATTLER = new Battler.Builder().withName("Doe").isFriendly(false).isMagician(true)
            .withAttack(10).withDefence(10).withSpeed(10).withHitPoint(10, 10).withSummonedElementBankSize(5)
            .withFreeElementBank(new FreeElementBank(5)).withController(new BattlerControllerMock()).withObserver(new
                    BattlerObserverMock()).build();

    private BattleField battleField;

    @Before
    public void setUp() {
        Set<Battler> battlers = new HashSet<>();
        battlers.add(FRIENDLY_BATTLER);
        battlers.add(ENEMY_BATTLER);

        battleField = new BattleField(battlers, Environment.BALANCED);
    }

    @Test
    public void getEnvironment_WithCorrectEnvironment() {
        assertThat(battleField.getEnvironment()).isEqualTo(Environment.BALANCED);
    }

    @Test
    public void getAllBattlers_WithCorrectBattlers() {
        assertThat(battleField.getAllBattlers()).containsExactlyInAnyOrder(FRIENDLY_BATTLER, ENEMY_BATTLER);
    }

    @Test
    public void getLiveBattlers_WithCorrectLiveBattlers() {
        FRIENDLY_BATTLER.getHitPoint().setValue(0);

        assertThat(battleField.getLiveBattlers()).doesNotContain(FRIENDLY_BATTLER);
    }

    @Test
    public void getFriendlyBattlerNumber_WithCorrectValue() {
        ENEMY_BATTLER.getHitPoint().setValue(0);

        assertThat(battleField.getFriendlyBattlerNumber()).isEqualTo(1);
    }

    @Test
    public void getEnemyBattlerNumber_WithCorrectValue() {
        ENEMY_BATTLER.getHitPoint().setValue(0);

        assertThat(battleField.getEnemyBattlerNumber()).isEqualTo(0);
    }

    @Test
    public void getEnemiesFor() {
        assertThat(battleField.getEnemiesFor(ENEMY_BATTLER)).containsExactlyInAnyOrder(FRIENDLY_BATTLER);
    }

    @Test
    public void getBattler_WithCorrectBattler() {
        assertThat(battleField.getBattler("John")).isEqualTo(FRIENDLY_BATTLER);
        assertThat(battleField.getBattler("Doe")).isEqualTo(ENEMY_BATTLER);
    }

    @Test
    public void addBattler_CorrectBattler() {
        Battler battler = new Battler.Builder().withName("Roe").isFriendly(true).isMagician(true).withAttack(10)
                .withDefence(10).withSpeed(10).withHitPoint(10, 10).withSummonedElementBankSize(5)
                .withFreeElementBank(new FreeElementBank(5)).withController(new BattlerControllerMock()).withObserver
                        (new BattlerObserverMock()).build();

        battleField.addBattler(battler);

        assertThat(battleField.getBattler("Roe")).isEqualTo(battler);
    }
}