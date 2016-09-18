package xigua.battle.of.elements.model.battle;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ElementBankTest {
    private final ElementBankForTest elementBank = new ElementBankForTest(3);

    @Before
    public void setUp() {
        elementBank.add(Element.FIRE);
        elementBank.add(Element.WATER);
        elementBank.add(Element.WOOD);
    }

    @Test
    public void getMaxSize_WithCorrectSizeLimit() {
        assertThat(elementBank.getMaxSize()).isEqualTo(3);
    }

    @Test
    public void getCurrentSize_WithCorrectNumberOfElements() {
        assertThat(elementBank.getCurrentSize()).isEqualTo(3);
    }

    @Test
    public void add_BankContainsCorrectElements() {
        assertThat(elementBank.getElementList().get(0)).isEqualTo(Element.FIRE);
        assertThat(elementBank.getElementList().get(1)).isEqualTo(Element.WATER);
        assertThat(elementBank.getElementList().get(2)).isEqualTo(Element.WOOD);
    }

    @Test
    public void addWhenBankIsFull_ExceptionThrown() {
        assertThatThrownBy(() -> elementBank.add(Element.FIRE)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void remove_ElementDoesNotExist() {
        Element element = elementBank.remove(0);

        assertThat(element).isEqualTo(Element.FIRE);
        assertThat(elementBank.getElementList().get(0)).isEqualTo(Element.WATER);
        assertThat(elementBank.getCurrentSize()).isEqualTo(2);
    }

    @Test
    public void clear_CurrentSizeIsZero() {
        elementBank.clear();

        assertThat(elementBank.getCurrentSize()).isEqualTo(0);
    }

    private static class ElementBankForTest extends ElementBank {
        private ElementBankForTest(int maxSize) {
            super(maxSize);
        }

        private List<Element> getElementList() {
            return Collections.unmodifiableList(elementList);
        }
    }
}
