package xigua.battle.of.elements.logic.battle.actions;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;

public class CommonRules {
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
