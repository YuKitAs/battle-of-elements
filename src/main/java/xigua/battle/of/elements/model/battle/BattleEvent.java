package xigua.battle.of.elements.model.battle;

import xigua.battle.of.elements.utility.DeepCopy;

import java.util.HashMap;
import java.util.Map;

public enum BattleEvent {
    BATTLE_START;

    private final Map<String, byte[]> attributes = new HashMap<>();

    public void putAttribute(String key, Object value) {
        attributes.put(key, DeepCopy.toBytes(value));
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }
}
