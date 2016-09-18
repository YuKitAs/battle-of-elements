package xigua.battle.of.elements.model.battle;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SummonedElementBankTest {
    private SummonedElementBank elementBank;

    @Before
    public void setUp() {
        elementBank = new SummonedElementBank(4);

        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WOOD);
    }

    @Test
    public void getMaxSize_WithCorrectSizeLimit() {
        assertThat(elementBank.getMaxSize()).isEqualTo(4);
    }

    @Test
    public void getCurrentSize_WithCorrectNumberOfElements() {
        assertThat(elementBank.getCurrentSize()).isEqualTo(3);
    }

    @Test
    public void add_BankContainsCorrectElements() {
        assertThat(elementBank.getFirst()).isEqualTo(Element.FIRE);
        assertThat(elementBank.getSecond()).isEqualTo(Element.WATER);
        assertThat(elementBank.getLast()).isEqualTo(Element.WOOD);
    }

    @Test
    public void addWhenBankIsFull_ExceptionThrown() {
        assertThatThrownBy(() -> {
            elementBank.add(Element.FIRE);
            elementBank.add(Element.WATER);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void clear_ExceptionThrownWhileGetFirst() {
        elementBank.clear();

        assertThatThrownBy(() -> elementBank.getFirst()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void getElementLevelCount_WithCorrectNumber() {
        elementBank.add(Element.FIRE);

        assertThat(elementBank.getElementLevelCount(Element.FIRE)).isEqualTo(1);
    }
}