package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface BattlerController {
    void setBattler(Battler battler);

    Choices choose(Choices choices);
}
