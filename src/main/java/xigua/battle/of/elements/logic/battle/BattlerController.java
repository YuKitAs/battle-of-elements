package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.battle.actions.Action;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.List;
import java.util.Set;

public interface BattlerController {
    ElementConsumeChoice chooseSummonedElement(List<ElementConsumeChoice> elementChoices);

    Action chooseAction(Set<Action> actions);

    String chooseTarget(Set<String> enemyNames);
}
