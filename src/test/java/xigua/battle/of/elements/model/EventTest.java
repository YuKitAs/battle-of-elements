package xigua.battle.of.elements.model;

import org.junit.Test;
import xigua.battle.of.elements.utility.DummySerializable;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    private static final String ATTRIBUTE_KEY = "foo";
    private static final int ID = 0;
    private static final int INNER_VALUE = 42;

    @Test
    public void putAndGetAttribute_WithCorrectAttribute() {
        DummySerializable obj = new DummySerializable(ID, INNER_VALUE);

        Event event = new Event(EventType.BATTLE_STARTED);
        event.putAttribute(ATTRIBUTE_KEY, obj);
        DummySerializable deserialized = (DummySerializable) event.getAttribute(ATTRIBUTE_KEY);

        assertThat(event.getType()).isEqualTo(EventType.BATTLE_STARTED);

        assertThat(deserialized.getId()).isEqualTo(ID);
        assertThat(deserialized.getInnerValue().getId()).isEqualTo(INNER_VALUE);
    }
}