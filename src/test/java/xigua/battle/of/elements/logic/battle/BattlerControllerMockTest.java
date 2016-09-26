package xigua.battle.of.elements.logic.battle;

import org.junit.Before;
import org.junit.Test;
import xigua.battle.of.elements.logic.battle.actions.Action;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BattlerControllerMockTest {
    private final BattlerControllerMock battlerControllerMock = new BattlerControllerMock();
    private final List<ElementConsumeChoice> elementConsumeChoices = new ArrayList<>();
    private final Set<Action> actions = new LinkedHashSet<>();
    private final Set<String> enemyNames = new LinkedHashSet<>();

    @Before
    public void setUp() {
        elementConsumeChoices.add(new ElementConsumeChoice(Element.FIRE, ElementConsumeChoice.Source.GENERATED, 1));
        elementConsumeChoices.add(new ElementConsumeChoice(Element.WATER, ElementConsumeChoice.Source.GENERATED, 2));
        elementConsumeChoices.add(new ElementConsumeChoice(Element.WOOD, ElementConsumeChoice.Source
                .FREE_ELEMENT_BANK, 3));

        actions.add(Action.ABSORB_ELEMENT);
        actions.add(Action.PHYSICAL_ATTACK);

        enemyNames.add("foo");
        enemyNames.add("bar");
    }

    @Test
    public void chooseSummonedElement_WithCorrectElement() {
        assertThat(battlerControllerMock.chooseSummonedElement(elementConsumeChoices).getElement()).isEqualTo(Element
                .FIRE);
        assertThat(battlerControllerMock.chooseSummonedElement(elementConsumeChoices).getSource()).isEqualTo
                (ElementConsumeChoice.Source.GENERATED);
        assertThat(battlerControllerMock.chooseSummonedElement(elementConsumeChoices).getNumber()).isEqualTo(3);
    }

    @Test
    public void chooseAction_WithCorrectAction() {
        assertThat(battlerControllerMock.chooseAction(actions)).isEqualTo(Action.ABSORB_ELEMENT);
        assertThat(battlerControllerMock.chooseAction(actions)).isEqualTo(Action.PHYSICAL_ATTACK);
    }

    @Test
    public void chooseTarget_WithCorrectTarget() {
        assertThat(battlerControllerMock.chooseTarget(enemyNames)).isEqualTo("foo");
        assertThat(battlerControllerMock.chooseTarget(enemyNames)).isEqualTo("bar");
    }

    @Test
    public void getElementChoicesReceived_WithCorrectElementChoices() {
        battlerControllerMock.chooseSummonedElement(elementConsumeChoices);

        assertThat(battlerControllerMock.getElementChoicesReceived()).isEqualTo(elementConsumeChoices);
    }

    @Test
    public void getActionsReceived_WithCorrectActions() {
        battlerControllerMock.chooseAction(actions);

        assertThat(battlerControllerMock.getActionsReceived()).isEqualTo(actions);
    }

    @Test
    public void getEnemyNamesReceived_WithCorrectEnemyNames() {
        battlerControllerMock.chooseTarget(enemyNames);

        assertThat(battlerControllerMock.getEnemyNamesReceived()).isEqualTo(enemyNames);
    }
}
