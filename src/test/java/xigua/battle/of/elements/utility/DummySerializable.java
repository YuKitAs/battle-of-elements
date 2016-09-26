package xigua.battle.of.elements.utility;

import java.io.Serializable;

public class DummySerializable implements Serializable {
    private final int id;
    private final InnerValue innerValue;

    public DummySerializable(int id, int childId) {
        this.id = id;
        innerValue = new InnerValue(childId);
    }

    public int getId() {
        return id;
    }

    public InnerValue getInnerValue() {
        return innerValue;
    }

    public static class InnerValue implements Serializable {
        private final int id;

        InnerValue(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
