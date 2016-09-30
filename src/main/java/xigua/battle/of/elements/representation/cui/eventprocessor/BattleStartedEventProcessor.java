package xigua.battle.of.elements.representation.cui.eventprocessor;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.DisplayHelper;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public class BattleStartedEventProcessor implements EventProcessor {
    @Override
    public void process(Battler battler, Event event) {
        BattleField battleField = (BattleField) event.getAttribute("battleField");
        String battlerNames = DisplayHelper.getBattleFieldDisplay(battleField);

        InteractionHelper.printEmptyAndLine("战斗开始了！");
        InteractionHelper.printEmptyAndLine(String.format("战场上有：%s", battlerNames));
    }
}
