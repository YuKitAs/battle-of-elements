package xigua.battle.of.elements.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepCopy {
    public static <T extends Serializable> T copy(T object) {
        byte[] objectBytes = toBytes(object);
        return fromBytes(objectBytes);
    }

    private static byte[] toBytes(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutput.writeObject(object);
            objectOutput.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Cannot serialize object.");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Serializable> T fromBytes(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
            return (T) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot deserialize object.");
        }
    }
}
