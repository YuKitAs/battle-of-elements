package xigua.battle.of.elements.utility;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeepCopyTest {
    @Test
    public void copy() {
        DummySerializable serializableObject = new DummySerializable(0, 42);
        DummySerializable deepCopiedObject = DeepCopy.copy(serializableObject);

        assertThat(serializableObject.getId()).isEqualTo(deepCopiedObject.getId());
        assertThat(serializableObject.getInnerValue().getId()).isEqualTo(deepCopiedObject.getInnerValue().getId());

        assertThat(serializableObject == deepCopiedObject).isFalse();
    }

}