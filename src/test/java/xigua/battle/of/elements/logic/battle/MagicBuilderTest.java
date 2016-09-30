package xigua.battle.of.elements.logic.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MagicBuilderTest {
    private final SummonedElementBank summonedElementBank = new SummonedElementBank(7);

    @Before
    public void setUp() {
        summonedElementBank.add(Element.FIRE);
        summonedElementBank.add(Element.WATER);
    }

    @Test
    public void canBuildWithLessThanThreeElements_ReturnFalse() {
        assertThat(MagicBuilder.canBuildMagic(summonedElementBank)).isFalse();
    }

    @Test
    public void buildWithNoMoreThanThreeElements_EmptyMagic() {
        summonedElementBank.add(Element.WOOD);

        assertThat(MagicBuilder.buildFromSummonedElementBank(summonedElementBank).isEmpty()).isTrue();
    }

    @Test
    public void buildWithWrongEndElement_ExceptionThrown() {
        summonedElementBank.add(Element.FIRE);
        summonedElementBank.add(Element.FIRE);

        assertThatThrownBy(() -> MagicBuilder.buildFromSummonedElementBank(summonedElementBank)).isInstanceOf
                (RuntimeException.class);
    }

    @Test
    public void buildWithDuplicatedEndElement_EmptyMagic() {
        summonedElementBank.add(Element.WOOD);
        summonedElementBank.add(Element.WOOD);

        assertThat(MagicBuilder.buildFromSummonedElementBank(summonedElementBank).isEmpty()).isTrue();
    }

    @Test
    public void buildWithMoreThanThreeElements_ReturnCorrectMagic() {
        summonedElementBank.add(Element.FIRE);
        summonedElementBank.add(Element.WATER);
        summonedElementBank.add(Element.WATER);
        summonedElementBank.add(Element.FIRE);
        summonedElementBank.add(Element.WOOD);

        Magic magic = MagicBuilder.buildFromSummonedElementBank(summonedElementBank);

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
