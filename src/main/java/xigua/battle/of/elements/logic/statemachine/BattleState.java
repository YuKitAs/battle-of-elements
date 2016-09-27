package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.logic.battle.BattlerController;
import xigua.battle.of.elements.model.MessageBox;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.HashMap;
import java.util.Map;

public class BattleState implements GameState {
    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        Battler battler1 = new Battler.Builder().withName("Foo").isFriendly(true).isMagician(true).withAttack(10)
                .withDefence(10).withFreeElementBank(new FreeElementBank(10)).withSummonedElementBankSize(6)
                .withHitPoint(100, 100).withSpeed(10).build();
        Battler battler2 = new Battler.Builder().withName("Bar").isFriendly(false).isMagician(true).withAttack(10)
                .withDefence(10).withFreeElementBank(new FreeElementBank(10)).withSummonedElementBankSize(6)
                .withHitPoint(100, 100).withSpeed(10).build();

        Map<Battler, BattlerController> battlerControllerMap = new HashMap<>();
        //battlerControllerMap.put(battler1, new ConsoleBattlerController());
        //battlerControllerMap.put(battler2, new ConsoleBattlerController());

        //new BattleArbiter(Environment.BALANCED, battlerControllerMap).start();

        return ExitState.class;
    }
}
