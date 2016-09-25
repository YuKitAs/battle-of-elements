package xigua.battle.of.elements.logic.battle.actions;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.SummonedElementBank;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;
import xigua.battle.of.elements.model.battle.actions.Magic;

import static org.assertj.core.api.Assertions.assertThat;

public class MagicBuilderTest {
    private MagicBuilder magicBuilder;
    private SummonedElementBank elementBank;

    @Before
    public void setUp() {
        magicBuilder = new MagicBuilder();
        elementBank = new SummonedElementBank(5);

        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
    }

    @Test
    public void buildWithTwoElements_EmptyMagic() {
        assertThat(magicBuilder.fromSummonedElementBank(elementBank)).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildWithWrongEndElement_EmptyMagic() {
        elementBank.add(Element.WOOD);

        assertThat(magicBuilder.fromSummonedElementBank(elementBank)).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildWithDuplicatedEndElement_EmptyMagic() {
        elementBank.add(Element.WATER);
        elementBank.add(Element.WATER);

        assertThat(magicBuilder.fromSummonedElementBank(elementBank)).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildWithNoMoreThanThreeElements_EmptyMagic() {
        elementBank.add(Element.WATER);

        assertThat(magicBuilder.fromSummonedElementBank(elementBank)).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildWithMoreThanThreeElements_ReturnCorrectMagic() {
        elementBank.add(Element.WOOD);
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);

        Magic magic = magicBuilder.fromSummonedElementBank(elementBank);

        assertThat(magic.getUsage()).isEqualTo(ElementUsage.ATTACK);
        assertThat(magic.getType()).isEqualTo(Element.WATER);

        assertThat(magic.getPrimaryLevel()).isEqualTo(1);
        assertThat(magic.getSecondaryLevel()).isEqualTo(1);
    }

    @Test
    public void buildPhysicalAttackMagic_ReturnCorrectMagic() {
        Magic magic = magicBuilder.physicalAttackMagic(3);

        assertThat(magic.getUsage()).isEqualTo(ElementUsage.ATTACK);
        assertThat(magic.getType()).isEqualTo(Element.NONE);

        assertThat(magic.getPrimaryLevel()).isEqualTo(3);
        assertThat(magic.getSecondaryLevel()).isEqualTo(0);
    }
}
