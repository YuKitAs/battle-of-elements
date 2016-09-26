package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;
import xigua.battle.of.elements.model.battle.actions.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

public class BattlerObserverMock implements BattlerObserver {

    @Override
    public void notifyBattleStarted(Battler currentBattler, BattleField battleField) {

    }

    @Override
    public void notifyRoundStarted(Battler currentBattler, BattleField battleField) {

    }

    @Override
    public void notifyRoundEnded(Battler currentBattler, BattleField battleField) {

    }

    @Override
    public void notifyAfterChoseElement(ElementConsumeChoice choice, Battler currentBattler, BattleField battleField) {

    }

    @Override
    public void notifyAfterBuiltMagic(Magic magic, Battler currentBattler, BattleField battleField) {

    }

    @Override
    public void notifyAppliedMagic(ElementUsage usage, Battler targetBefore, Battler targetAfter, Battler
            currentBattler, BattleField battleField) {

    }
}