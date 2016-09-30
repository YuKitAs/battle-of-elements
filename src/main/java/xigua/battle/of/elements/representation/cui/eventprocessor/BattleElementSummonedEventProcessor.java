package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.DisplayHelper;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public class BattleElementSummonedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        Battler battlerInTurn = (Battler) event.getAttribute("battlerInTurn");
        Element summonedElement = (Element) event.getAttribute("summonedElement");

        if (summonedElement == null) {
            InteractionHelper.printEmptyAndLine(String.format("%s什么元素都没有召唤", DisplayHelper.getBattlerNameDisplay
                    (battlerInTurn)));
            return;
        }

        if (battler.equals(battlerInTurn)) {
            InteractionHelper.printEmptyAndLine(String.format("%s召唤了%s", DisplayHelper.getBattlerNameDisplay
                    (battlerInTurn), DisplayHelper.getElementDisplay(summonedElement)));
        } else {
            InteractionHelper.printEmptyAndLine(String.format("%s召唤了一个元素", DisplayHelper.getBattlerNameDisplay
                    (battlerInTurn)));
        }
    }
}
