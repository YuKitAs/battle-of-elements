package xigua.battle.of.elements.logic.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MagicFactoryTest {
    private MagicFactory magicFactory;
    private SummonedElementBank elementBank;

    @Before
    public void setUp() {
        magicFactory = new MagicFactory();
        elementBank = new SummonedElementBank(5);

        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
    }

    @Test
    public void buildWithTwoElements_ExceptionThrown() {
        assertThatThrownBy(() -> magicFactory.buildFromSummonedElementBank(elementBank)).isInstanceOf
                (RuntimeException.class).hasMessage("Too few elements for casting magic.");
    }

    @Test
    public void buildWithWrongEndElement_ExceptionThrown() {
        elementBank.add(Element.WOOD);

        assertThatThrownBy(() -> magicFactory.buildFromSummonedElementBank(elementBank)).isInstanceOf
                (RuntimeException.class).hasMessage("Element bank is not ended properly.");
    }

    @Test
    public void buildWithDuplicatedEndElement_ExceptionThrown() {
        elementBank.add(Element.WATER);
        elementBank.add(Element.WATER);

        assertThatThrownBy(() -> magicFactory.buildFromSummonedElementBank(elementBank)).isInstanceOf
                (RuntimeException.class).hasMessage("Invalid element bank for casting magic.");
    }

    @Test
    public void buildWithNoMoreThanThreeElements_ReturnEmptyMagic() {
        elementBank.add(Element.WATER);

        Magic magic = magicFactory.buildFromSummonedElementBank(elementBank);

        assertThat(magic).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildWithMoreThanThreeElements_ReturnCorrectMagic() {
        elementBank.add(Element.WOOD);
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);

        Magic magic = magicFactory.buildFromSummonedElementBank(elementBank);

        assertThat(magic.getEffectElement()).isEqualTo(Element.FIRE);
        assertThat(magic.getTypeElement()).isEqualTo(Element.WATER);

        assertThat(magic.getPrimaryLevel()).isEqualTo(1);
        assertThat(magic.getSecondaryLevel()).isEqualTo(1);
    }

    @Test
    public void buildNonTypedMagic() {
        Magic magic = magicFactory.buildNonTypedMagic(Element.FIRE, 3);

        assertThat(magic.getEffectElement()).isEqualTo(Element.FIRE);
        assertThat(magic.getTypeElement()).isEqualTo(Element.NONE);

        assertThat(magic.getPrimaryLevel()).isEqualTo(3);
        assertThat(magic.getSecondaryLevel()).isEqualTo(0);
    }
}
