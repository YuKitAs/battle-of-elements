package xigua.battle.of.elements.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class DeepCopy {
    @SuppressWarnings("unchecked")
    public static <T> T copy(T object) {
        byte[] objectBytes;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutput.writeObject(object);
            objectOutput.flush();
            objectBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Cannot deep copy object.");
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytes);
        try (ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
            return (T) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot deep copy object.");
        }
    }
}
