package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface EventProcessor {
    void process(Battler battler, Event event);
}
