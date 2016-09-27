package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.LinkedList;
import java.util.List;

public class BattlerObserverMock implements BattlerObserver {
    private final List<Event> eventReceived = new LinkedList<>();

    @Override
    public void setBattler(Battler battler) {
        // Simply do nothing here.
    }

    @Override
    public void notify(Event event) {
        eventReceived.add(event);
    }

    public List<Event> getEventReceived() {
        return eventReceived;
    }
}