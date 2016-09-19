package xigua.battle.of.elements.model.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.model.battle.magic.Element;
import xigua.battle.of.elements.model.battle.magic.SummonedElementBank;

import static org.assertj.core.api.Assertions.assertThat;

public class SummonedElementBankTest {
    private final SummonedElementBank elementBank = new SummonedElementBank(4);

    @Before
    public void setUp() {
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WOOD);
    }

    @Test
    public void getElements_HasCorrectOrder() {
        assertThat(elementBank.getFirst()).isEqualTo(Element.FIRE);
        assertThat(elementBank.getSecond()).isEqualTo(Element.WATER);
        assertThat(elementBank.getLast()).isEqualTo(Element.WOOD);
    }

    @Test
    public void getElementLevelCount_WithCorrectNumber() {
        elementBank.add(Element.FIRE);

        assertThat(elementBank.getElementLevelCount(Element.FIRE)).isEqualTo(1);
    }
}