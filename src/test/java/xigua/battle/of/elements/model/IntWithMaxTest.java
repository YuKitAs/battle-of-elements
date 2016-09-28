package xigua.battle.of.elements.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntWithMaxTest {
    private IntWithMax intWithMax;

    @Before
    public void setUp() {
        intWithMax = new IntWithMax(42);
    }

    @Test
    public void getValue_WithCorrectValue() {
        assertThat(intWithMax.getValue()).isEqualTo(42);
    }

    @Test
    public void setWhenValueBiggerThanMax_CorrectValueSet() {
        intWithMax.setValue(100);
        assertThat(intWithMax.getValue()).isEqualTo(42);
    }

    @Test
    public void setWhenValueSmallerThanMax_CorrectValueSet() {
        intWithMax.setValue(1);
        assertThat(intWithMax.getValue()).isEqualTo(1);
    }

    @Test
    public void getMax_WithCorrectMax() {
        assertThat(intWithMax.getMaxValue()).isEqualTo(42);
    }
}
