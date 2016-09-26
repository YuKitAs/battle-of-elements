package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface BattlerObserver {
    void setBattler(Battler battler);

    void notify(Event event);
}
