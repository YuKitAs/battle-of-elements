package xigua.battle.of.elements.model;

import xigua.battle.of.elements.utility.DeepCopy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private final EventType type;
    private final Map<String, Serializable> attributes = new HashMap<>();

    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void putAttribute(String key, Serializable value) {
        attributes.put(key, DeepCopy.copy(value));
    }

    public Serializable getAttribute(String key) {
        return attributes.get(key);
    }
}
