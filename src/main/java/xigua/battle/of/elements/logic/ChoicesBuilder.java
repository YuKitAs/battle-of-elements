package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.ChoicePurpose;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.Action;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.SummonedElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            FreeElementBank freeElementBank, SummonedElementBank summonedElementBank) {
        List<String> choiceStrings = new ArrayList<>();

        generatedElements.forEach(element -> choiceStrings.add(String.format("%s:%s:1", element.name(), ElementSource
                .GENERATED)));
        freeElementBank.toDistinctList().forEach( //
                element -> choiceStrings.add(String.format("%s:%s:%d", element.name(), ElementSource.STORED,
                        freeElementBank.countElement(element))));
        summonedElementBank.toList().forEach( //
                element -> choiceStrings.add(String.format("%s:%s:1", element.name(), ElementSource.SUMMONED)));

        choiceStrings.add("NONE:NONE:1");

        return new Choices(purpose, choiceStrings);
    }

    public static Choices buildMagicTargetChoices(List<Battler> targetBattlers) {
        return new Choices(ChoicePurpose.BATTLE_MAGIC_TARGET, targetBattlers.stream().map(Battler::getName).collect
                (Collectors.toList()));
    }

    public enum ElementSource {
        GENERATED, STORED, SUMMONED
    }
}
