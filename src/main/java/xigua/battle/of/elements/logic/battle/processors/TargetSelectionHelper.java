package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.ChoicesPurpose;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class TargetSelectionHelper {
    protected static Choices buildTargetSelectionChoices(Set<Battler> potentialTargets) {
        List<String> choiceStrings = new ArrayList<>();

        potentialTargets.forEach(battler -> choiceStrings.add(battler.getName()));

        return new Choices(ChoicesPurpose.BATTLE_MAGIC_TARGET, choiceStrings);
    }
}
