package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.DisplayHelper;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public class BattleActionStartedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        BattleField battleField = (BattleField) event.getAttribute("battleField");
        Battler battlerInTurn = (Battler) event.getAttribute("battlerInTurn");

        String battlerName = battlerInTurn.getName();
        String summonedElements = DisplayHelper.getSummonedElementBankDisplay(battlerInTurn.getSummonedElementBank());

        if (summonedElements.isEmpty()) {
            summonedElements = "<空>";
        }

        InteractionHelper.printEmptyAndLine(String.format("现在是%s的回合", battlerName));

        if (battler.equals(battlerInTurn)) {
            InteractionHelper.printEmptyAndLine(String.format("%s已经召唤的元素：%s", battlerName, summonedElements));
        }
    }
}
