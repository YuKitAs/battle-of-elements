package xigua.battle.of.elements.model.battle;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeElementBankTest {
    private final FreeElementBank freeElementBank = new FreeElementBank(3);

    @Before
    public void setUp() {
        freeElementBank.add(Element.FIRE);
        freeElementBank.add(Element.FIRE);
        freeElementBank.add(Element.WATER);
    }

    @Test
    public void hasElement() {
        assertThat(freeElementBank.hasElement(Element.FIRE)).isTrue();
        assertThat(freeElementBank.hasElement(Element.WOOD)).isFalse();
    }

    @Test
    public void countElement_WithCorrectElementNumber() {
        assertThat(freeElementBank.countElement(Element.FIRE)).isEqualTo(2);
    }

    @Test
    public void delete_ElementDoesNotExist() {
        freeElementBank.deleteElement(Element.FIRE);

        assertThat(freeElementBank.countElement(Element.FIRE)).isEqualTo(1);
    }

    @Test
    public void deleteNonExistentElement_ExceptionThrown() {
        assertThatThrownBy(() -> freeElementBank.deleteElement(Element.WOOD)).isInstanceOf(RuntimeException.class)
                .hasMessage("No such element in element bank.");
    }

    @Test
    public void toDistinctList_WithDistinctElements() {
        assertThat(freeElementBank.toDistinctList()).containsExactly(Element.FIRE, Element.WATER);
    }
}