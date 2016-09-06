package xigua.battle.of.elements.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SummonedElementBankTest {
    private SummonedElementBank summonedElementBank;

    @Before
    public void setUp() {
        summonedElementBank = new SummonedElementBank(2);
    }

    @Test
    public void add_HasAListWithCorrectElements() throws Exception {
        addTwoElementsToBank();

        assertThat(summonedElementBank.toList().get(0)).isEqualTo(Element.FIRE);
        assertThat(summonedElementBank.toList().get(1)).isEqualTo(Element.WATER);
    }

    @Test
    public void addWhenElementBankIsFull_ExceptionThrown() {
        addTwoElementsToBank();

        assertThatThrownBy(() -> summonedElementBank.add(Element.WOOD)).isInstanceOf(SummonedElementBankFullException.class);
    }

    @Test
    public void getLast_HasCorrectElement() throws Exception {
        addTwoElementsToBank();

        assertThat(summonedElementBank.getLast()).isEqualTo(Element.WATER);
    }

    @Test
    public void toList_ResultListUnmodifiable() throws Exception {
        assertThat(summonedElementBank.toList()).isInstanceOf(List.class);
        assertThatThrownBy(() -> summonedElementBank.toList().add(Element.FIRE)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void clear_WithNoElementsLeft() throws Exception {
        addTwoElementsToBank();
        summonedElementBank.clear();

        assertThat(summonedElementBank.toList()).isEmpty();
    }

    private void addTwoElementsToBank() {
        summonedElementBank.add(Element.FIRE);
        summonedElementBank.add(Element.WATER);
    }
}