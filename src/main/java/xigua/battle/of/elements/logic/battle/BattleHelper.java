package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;

public final class BattleHelper {
    public static void notifyAllBattlers(BattleField battleField, Event event) {
        battleField.getAllBattlers().forEach(battler -> battler.getObserver().notify(event));
    }
}
