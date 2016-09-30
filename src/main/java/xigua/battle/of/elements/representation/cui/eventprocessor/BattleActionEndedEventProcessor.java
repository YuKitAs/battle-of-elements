package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.DisplayHelper;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public class BattleActionEndedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        Battler battlerInTurn = (Battler) event.getAttribute("battlerInTurn");

        InteractionHelper.printEmptyAndLine(String.format("%s的回合结束了\n\n====================", DisplayHelper
                .getBattlerNameDisplay(battlerInTurn)));
    }
}
