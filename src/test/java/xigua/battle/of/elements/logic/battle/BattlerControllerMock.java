package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.battle.actions.Action;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BattlerControllerMock implements BattlerController {
    private List<ElementConsumeChoice> elementChoicesReceived = new ArrayList<>();
    private Queue<ElementConsumeChoice> elementConsumeChoices = new LinkedList<>();

    private Set<Action> actionsReceived = new LinkedHashSet<>();
    private Queue<Action> actions = new LinkedList<>();

    private Set<String> enemyNamesReceived = new LinkedHashSet<>();
    private Queue<String> targets = new LinkedList<>();

    @Override
    public ElementConsumeChoice chooseSummonedElement(List<ElementConsumeChoice> elementChoices) {
        elementChoicesReceived = elementChoices;

        elementChoices.forEach(choice -> elementConsumeChoices.add(choice));

        return elementConsumeChoices.poll();
    }

    @Override
    public Action chooseAction(Set<Action> actions) {
        actionsReceived = actions;

        actions.forEach(action -> this.actions.add(action));

        return this.actions.poll();
    }

    @Override
    public String chooseTarget(Set<String> enemyNames) {
        enemyNamesReceived = enemyNames;

        enemyNames.forEach(name -> targets.add(name));

        return targets.poll();
    }

    public List<ElementConsumeChoice> getElementChoicesReceived() {
        return elementChoicesReceived;
    }

    public Set<Action> getActionsReceived() {
        return actionsReceived;
    }

    public Set<String> getEnemyNamesReceived() {
        return enemyNamesReceived;
    }
}