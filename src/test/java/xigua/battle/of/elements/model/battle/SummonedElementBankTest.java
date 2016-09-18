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
    }

    @Test
    public void getMaxSize_WithCorrectSizeLimit() {
        assertThat(elementBank.getMaxSize()).isEqualTo(4);
    }

    @Test
    public void getCurrentSize_WithCorrectNumberOfElements() {
        addThreeElementsToBank();

        assertThat(elementBank.getCurrentSize()).isEqualTo(3);
    }

    @Test
    public void add_WithCorrectElement() {
        addThreeElementsToBank();

        assertThat(elementBank.elementList.get(0)).isEqualTo(Element.FIRE);
    }

    @Test
    public void add_WhenBankIsFull() {
        addThreeElementsToBank();

        assertThatThrownBy(() -> {
            elementBank.add(Element.FIRE);
            elementBank.add(Element.WATER);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void clear() {
        addThreeElementsToBank();
        elementBank.clear();

        assertThat(elementBank.elementList).isEmpty();
    }

    @Test
    public void getFirst_WithCorrectElement() {
        addThreeElementsToBank();

        assertThat(elementBank.getFirst()).isEqualTo(Element.FIRE);
    }

    @Test
    public void getSecond_WithCorrectElement() {
        addThreeElementsToBank();

        assertThat(elementBank.getSecond()).isEqualTo(Element.WATER);
    }

    @Test
    public void getLast_WithCorrectElement() {
        addThreeElementsToBank();

        assertThat(elementBank.getLast()).isEqualTo(Element.WOOD);
    }

    @Test
    public void getElementLevelCount_WithCorrectNumber() {
        addThreeElementsToBank();
        elementBank.add(Element.FIRE);

        assertThat(elementBank.getElementLevelCount(Element.FIRE)).isEqualTo(1);
    }

    private void addThreeElementsToBank() {
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WOOD);
    }
}