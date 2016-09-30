package xigua.battle.of.elements.logic.battle.actionprocessor;

import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface ActionProcessor {
    void process(BattleField battleField, Battler battlerInTurn);
}
