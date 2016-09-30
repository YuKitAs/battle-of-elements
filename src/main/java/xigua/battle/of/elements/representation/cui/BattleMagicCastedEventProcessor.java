package xigua.battle.of.elements.representation.cui;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.eventprocessor.EventProcessor;

public class BattleMagicCastedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        Battler battlerInTurn = (Battler) event.getAttribute("battlerInTurn");
        Magic magic = (Magic) event.getAttribute("magicCasted");

        if (battler.equals(battlerInTurn)) {
            InteractionHelper.printEmptyAndLine("现在已召唤的元素有：" + DisplayHelper.getSummonedElementBankDisplay(battlerInTurn
                    .getSummonedElementBank()));

        }

        if (magic.isEmpty()) {
            InteractionHelper.printEmptyAndLine("构成了空魔法");
            InteractionHelper.printLine("噗～一声轻响，魔法的光亮熄灭了");
            return;
        }
    }
}
