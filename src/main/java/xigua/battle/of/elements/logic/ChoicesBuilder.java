package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.logic.battle.actionprocessor.ElementSelectionHelper;
import xigua.battle.of.elements.model.ChoicePurpose;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.Action;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoicesBuilder {
    public static Choices buildActionChoices(Battler battler) {
        if (battler.isMagician()) {
            return new Choices(ChoicePurpose.BATTLE_ACTION, Arrays.asList(Action.SUMMON_ELEMENT.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        } else {
            return new Choices(ChoicePurpose.BATTLE_ACTION, Arrays.asList(Action.PHYSICAL_ATTACK.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        }
    }

    public static Choices buildElementSelectionChoices(ChoicePurpose purpose, List<Element> generatedElements,
            FreeElementBank freeElementBank) {
        List<String> choiceStrings = new ArrayList<>();

        generatedElements.forEach(element -> choiceStrings.add(String.format("%s:%s:1", element.name(),
                ElementSelectionHelper.GENERATED_ELEMENT)));
        freeElementBank.toDistinctList().forEach(element -> choiceStrings.add(String.format("%s:%s:%d", element.name
                (), ElementSelectionHelper.STORED_ELEMENT, freeElementBank.countElement(element))));

        return new Choices(purpose, choiceStrings);
    }
}
