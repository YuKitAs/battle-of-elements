package xigua.battle.of.elements.representation.cui.choiceprocessor;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.battler.Battler;

public interface ChoiceProcessor {
    Choices process(Battler battler, Choices choices);
}
