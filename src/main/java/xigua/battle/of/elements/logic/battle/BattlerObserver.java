package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;
import xigua.battle.of.elements.model.battle.actions.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface BattlerObserver {
    void notifyBattleStarted(Battler currentBattler, BattleField battleField);

    void notifyRoundStarted(Battler currentBattler, BattleField battleField);

    void notifyRoundEnded(Battler currentBattler, BattleField battleField);

    void notifyAfterChoseElement(ElementConsumeChoice choice, Battler currentBattler, BattleField battleField);

    void notifyAfterBuiltMagic(Magic magic, Battler currentBattler, BattleField battleField);

    void notifyAppliedMagic(ElementUsage usage, Battler targetBefore, Battler targetAfter, Battler currentBattler,
            BattleField battleField);
}
