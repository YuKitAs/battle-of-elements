package xigua.battle.of.elements.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class ElementListTest {
    private ElementList elementList;

    @Before
    public void setUp() {
        elementList = new ElementList(2);
    }

    @Test
    public void add() throws Exception {
        elementList.add(Element.FIRE);
        elementList.add(Element.WATER);

        assertThat(elementList.toList().get(0)).isEqualTo(Element.FIRE);
        assertThat(elementList.toList().get(1)).isEqualTo(Element.WATER);

        try {
            elementList.add(Element.WOOD);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ElementListFullException.class);
        }
    }

    @Test
    public void getLast() throws Exception {
        elementList.add(Element.FIRE);
        elementList.add(Element.WATER);

        assertThat(elementList.getLast()).isEqualTo(Element.WATER);
    }

    @Test
    public void toUnmodifiableList() throws Exception {
        assertThat(elementList.toList()).isInstanceOf(List.class);

        try {
            elementList.toList().add(Element.FIRE);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Test
    public void clear() throws Exception {
        elementList.add(Element.FIRE);
        elementList.clear();

        assertThat(elementList.toList()).isEmpty();
    }
}