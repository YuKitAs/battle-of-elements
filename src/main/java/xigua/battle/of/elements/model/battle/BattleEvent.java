package xigua.battle.of.elements.model.battle;

import xigua.battle.of.elements.utility.DeepCopy;

import java.util.HashMap;
import java.util.Map;

public class BattleEvent {
    private final String reciever;
    private final Map<String, byte[]> attributes = new HashMap<>();

    public BattleEvent(String reciever) {
        this.reciever = reciever;
    }

    public String getReciever() {
        return reciever;
    }

    public void putAttribute(String key, Object value) {
        attributes.put(key, DeepCopy.toBytes(value));
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }
}
