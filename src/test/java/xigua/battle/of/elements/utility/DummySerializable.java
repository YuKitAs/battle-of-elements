package xigua.battle.of.elements.utility;

import java.io.Serializable;

public class DummySerializable implements Serializable {
    private int id;
    private SerializableChild child;

    public DummySerializable(int id, int childId) {
        this.id = id;
        child = new SerializableChild(childId);
    }

    public int getId() {
        return id;
    }

    public SerializableChild getChild() {
        return child;
    }

    class SerializableChild implements Serializable {
        private int id;

        SerializableChild(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }
}
