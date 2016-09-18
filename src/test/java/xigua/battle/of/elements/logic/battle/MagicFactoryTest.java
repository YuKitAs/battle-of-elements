package xigua.battle.of.elements.logic.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import static org.assertj.core.api.Assertions.assertThat;

public class MagicFactoryTest {
    private MagicFactory magicFactory;
    private SummonedElementBank elementBank;
    private Magic magic;

    @Before
    public void setUp() {
        magicFactory = new MagicFactory();
        elementBank = new SummonedElementBank(5);

        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WOOD);
    }

    @Test
    public void buildFromSummonedElementBank_WithNoMoreThanThreeElements() {
        magic = magicFactory.buildFromSummonedElementBank(elementBank);

        assertThat(magic).isEqualTo(Magic.EMPTY);
    }

    @Test
    public void buildFromSummonedElementBank_WithMoreThanThreeElements() {
        elementBank.add(Element.FIRE);

        magic = magicFactory.buildFromSummonedElementBank(elementBank);

        assertThat(magic.getEffectElement()).isEqualTo(Element.FIRE);
        assertThat(magic.getTypeElement()).isEqualTo(Element.WATER);

        assertThat(magic.getPrimaryLevel()).isEqualTo(1);
        assertThat(magic.getSecondaryLevel()).isEqualTo(1);
    }

    @Test
    public void buildNonTypedMagic() {
        magic = magicFactory.buildNonTypedMagic(Element.FIRE, 3);

        assertThat(magic.getEffectElement()).isEqualTo(Element.FIRE);
        assertThat(magic.getTypeElement()).isEqualTo(Element.NONE);

        assertThat(magic.getPrimaryLevel()).isEqualTo(3);
        assertThat(magic.getSecondaryLevel()).isEqualTo(0);
    }
}
