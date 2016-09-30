package xigua.battle.of.elements.logic.statemachine;

import xigua.battle.of.elements.logic.battle.BattleArbiter;
import xigua.battle.of.elements.logic.battle.ai.VeryStupidAI;
import xigua.battle.of.elements.model.MessageBox;
import xigua.battle.of.elements.model.battle.Environment;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.BattleCli;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BattleState implements GameState {
    @Override
    public Class<? extends GameState> run(GameState lastState, MessageBox messageBox) {
        BattleCli battlerCli = new BattleCli();
        VeryStupidAI veryStupidAI = new VeryStupidAI();

        Battler battler1 = new Battler.Builder().withName("娜塔莉").isFriendly(true).isMagician(true).withAttack(10)
                .withDefence(10).withFreeElementBank(new FreeElementBank(10)).withSummonedElementBankSize(6)
                .withHitPoint(100, 100).withSpeed(12).withController(battlerCli).withObserver(battlerCli).build();
        Battler battler2 = new Battler.Builder().withName("学徒法师").isFriendly(false).isMagician(true).withAttack(10)
                .withDefence(10).withFreeElementBank(new FreeElementBank(10)).withSummonedElementBankSize(6)
                .withHitPoint(100, 100).withSpeed(10).withController(veryStupidAI).withObserver(veryStupidAI).build();

        new BattleArbiter(Environment.BALANCED, Stream.of(battler1, battler2).collect(Collectors.toSet())).start();

        return ExitState.class;
    }
}
