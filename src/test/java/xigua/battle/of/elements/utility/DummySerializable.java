package xigua.battle.of.elements.utility;

import java.io.Serializable;

class DummySerializable implements Serializable {
    private int id;
    private InnerValue innerValue;

    DummySerializable(int id, int childId) {
        this.id = id;
        innerValue = new InnerValue(childId);
    }

    int getId() {
        return id;
    }

    InnerValue getInnerValue() {
        return innerValue;
    }

    static class InnerValue implements Serializable {
        private int id;

        InnerValue(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }
}
