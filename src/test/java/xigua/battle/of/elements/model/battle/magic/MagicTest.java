package xigua.battle.of.elements.model.battle.magic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MagicTest {
    private Magic magic;
    private List<Magic.Effect> effects;

    @Before
    public void setUp() {
        effects = new ArrayList<>();
        effects.add(Magic.Effect.DAMAGE);
        effects.add(Magic.Effect.DEBUFF);

        magic = new Magic(Element.FIRE, effects);
    }

    @Test
    public void getElement_SameWithConstructorParameters() {
        assertThat(magic.getElement()).isEqualTo(Element.FIRE);
    }

    @Test
    public void getEffects__SameWithConstructorParameters() {
        assertThat(effects.get(0)).isEqualTo(Magic.Effect.DAMAGE);
        assertThat(effects.get(1)).isEqualTo(Magic.Effect.DEBUFF);
    }

    @Test
    public void getEffects_EffectListUnmodifiable() {
        List<Magic.Effect> effectList = magic.getEffects();
        assertThatThrownBy(() -> effectList.add(Magic.Effect.BLOCK)).isInstanceOf
                (UnsupportedOperationException.class);
    }
}
