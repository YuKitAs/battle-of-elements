package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.io.Serializable;

public interface BattlerObserver extends Serializable {
    void setBattler(Battler battler);

    void notify(Event event);
}
