package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.logic.battle.BattleHelper;
import xigua.battle.of.elements.logic.battle.ElementFactory;
import xigua.battle.of.elements.logic.battle.MagicBuilder;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.ChoicesPurpose;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.List;

public class SummonElementActionProcessor {

    private final Battler battler;
    private final BattleField battleField;
    private final ElementFactory elementFactory;

    public SummonElementActionProcessor(Battler battler, BattleField battleField, ElementFactory elementFactory) {
        this.battler = battler;
        this.battleField = battleField;
        this.elementFactory = elementFactory;
    }

    public void process() {
        List<Element> generatedElements = ElementSelectionHelper.generateElements(elementFactory);

        Choices elementSummonChoices = ElementSelectionHelper.buildElementSelectionChoices(ChoicesPurpose
                .BATTLE_SUMMON_ELEMENT, generatedElements, battler.getFreeElementBank());

        Choices elementSummonResult = battler.getController().choose(elementSummonChoices);

        Element summonedElement = ElementSelectionHelper.getSelectedElement(elementSummonResult, generatedElements,
                battler.getFreeElementBank());

        BattleHelper.notifyAllBattlers(battleField, buildElementSummonedEvent(battleField, battler, summonedElement));

        battler.getSummonedElementBank().add(summonedElement);

        Magic magic = MagicBuilder.fromSummonedElementBank(battler.getSummonedElementBank());

        BattleHelper.notifyAllBattlers(battleField, buildMagicCastedEvent(battleField, battler, magic));

        if (magic == Magic.EMPTY) {
            return;
        }


    }

    private Event buildElementSummonedEvent(BattleField battleField, Battler battlerInTurn, Element summonedElement) {
        Event result = new Event(EventType.BATTLE_ELEMENT_SUMMONED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("summonedElement", summonedElement);
        return result;
    }

    private Event buildMagicCastedEvent(BattleField battleField, Battler battlerInTurn, Magic magicCasted) {
        Event result = new Event(EventType.BATTLE_ELEMENT_SUMMONED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("magicCasted", magicCasted);
        return result;
    }
}
