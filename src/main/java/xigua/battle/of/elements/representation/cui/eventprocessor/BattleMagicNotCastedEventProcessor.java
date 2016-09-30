package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.DisplayHelper;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public class BattleMagicNotCastedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        Battler battlerInTurn = (Battler) event.getAttribute("battlerInTurn");
        if (battler.equals(battlerInTurn)) {
            InteractionHelper.printEmptyAndLine("现在已召唤的元素有：" + DisplayHelper.getSummonedElementBankDisplay(battlerInTurn
                    .getSummonedElementBank()));
        }

        InteractionHelper.printEmptyAndLine("无法构成魔法");
    }
}
