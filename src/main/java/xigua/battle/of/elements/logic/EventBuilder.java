package xigua.battle.of.elements.logic;

import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

public final class EventBuilder {
    public static Event buildBattleStartedEvent(BattleField battleField) {
        Event result = new Event(EventType.BATTLE_STARTED);
        result.putAttribute("battleField", battleField);
        return result;
    }

    public static Event buildActionStartedEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_STARTED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    public static Event buildActionEndedEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_ENDED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    public static Event buildElementSummonedEvent(BattleField battleField, Battler battlerInTurn, Element
            summonedElement) {
        Event result = new Event(EventType.BATTLE_ELEMENT_SUMMONED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("summonedElement", summonedElement);
        return result;
    }

    public static Event buileMagicNotCastedEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_MAGIC_NOT_CASTED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    public static Event buildMagicCastedEvent(BattleField battleField, Battler battlerInTurn, Magic magicCasted) {
        Event result = new Event(EventType.BATTLE_MAGIC_CASTED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("magicCasted", magicCasted);
        return result;
    }

    public static Event buildMagicTargetChosenEvent(BattleField battleField, Battler battlerInTurn, Magic magic,
            Battler target) {
        Event result = new Event(EventType.BATTLE_MAGIC_TARGET_CHOSEN);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("magic", magic);
        result.putAttribute("target", target);
        return result;
    }

    public static Event buildMagicProcessedEvent(BattleField battleField, Battler battlerInTurn, Battler
            battlerInTurnBefore, Magic magic, Battler target, Battler targetBefore) {
        Event result = new Event(EventType.BATTLE_AFTER_MAGIC);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurnBefore", battlerInTurnBefore);
        result.putAttribute("magic", magic);
        result.putAttribute("target", target);
        result.putAttribute("targetBefore", targetBefore);
        return result;
    }
}
