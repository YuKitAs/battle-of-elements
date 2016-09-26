package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;

public class BattleHelper {
    private final BattleField battleField;

    public BattleHelper(BattleField battleField) {
        this.battleField = battleField;
    }

    public void notifyAllBattlers(Event event) {
        battleField.getAllBattlers().forEach(battler -> battler.getObserver().notify(event));
    }

    public static ElementUsage getElementUsage(Element element) {
        switch (element) {
            case FIRE:
                return ElementUsage.ATTACK;
            case WATER:
                return ElementUsage.DEFEND;
            case WOOD:
                return ElementUsage.HEAL;
            default:
                return ElementUsage.NONE;
        }
    }
}
