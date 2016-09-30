package xigua.battle.of.elements.logic.battle;

import org.junit.Before;
import org.junit.Test;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import static org.assertj.core.api.Assertions.assertThat;

public class MagicBuilderTest {
    private SummonedElementBank elementBank;

    @Before
    public void setUp() {
        elementBank = new SummonedElementBank(7);

        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
    }

    @Test
    public void buildWithNoMoreThanThreeElements_EmptyMagic() {
        elementBank.add(Element.WOOD);

        assertThat(MagicBuilder.buildFromSummonedElementBank(elementBank).isEmpty()).isTrue();
    }

    @Test
    public void buildWithWrongEndElement_EmptyMagic() {
        elementBank.add(Element.FIRE);
        elementBank.add(Element.FIRE);

        assertThat(MagicBuilder.buildFromSummonedElementBank(elementBank).isEmpty()).isTrue();
    }

    @Test
    public void buildWithDuplicatedEndElement_EmptyMagic() {
        elementBank.add(Element.WOOD);
        elementBank.add(Element.WOOD);

        assertThat(MagicBuilder.buildFromSummonedElementBank(elementBank).isEmpty()).isTrue();
    }

    @Test
    public void buildWithMoreThanThreeElements_ReturnCorrectMagic() {
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WATER);
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WOOD);

        Magic magic = MagicBuilder.buildFromSummonedElementBank(elementBank);

        assertThat(magic.getUsage()).isEqualTo(ElementUsage.ATTACK);
        assertThat(magic.getType()).isEqualTo(Element.WATER);

        assertThat(magic.getPrimaryLevel()).isEqualTo(2);
        assertThat(magic.getSecondaryLevel()).isEqualTo(2);
    }

    @Test
    public void buildPhysicalAttackMagic_ReturnCorrectMagic() {
        Magic magic = MagicBuilder.buildPhysicalAttack(3);

        assertThat(magic.getUsage()).isEqualTo(ElementUsage.ATTACK);
        assertThat(magic.getType()).isEqualTo(Element.NONE);

        assertThat(magic.getPrimaryLevel()).isEqualTo(3);
        assertThat(magic.getSecondaryLevel()).isEqualTo(0);
    }
}
