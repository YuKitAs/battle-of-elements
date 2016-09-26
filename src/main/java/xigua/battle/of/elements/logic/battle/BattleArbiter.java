package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.ChoicesPurpose;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.Action;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Environment;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BattleArbiter {
    private final BattleField battleField;
    private final BattleHelper battleHelper;

    public BattleArbiter(Environment environment, Set<Battler> battlers) {
        battleField = new BattleField(battlers, environment);
        battleHelper = new BattleHelper(battleField);
    }

    public void start() {
        battleHelper.notifyAllBattlers(buildBattleStartEvent(battleField));

        while (!battleField.friendTeamWins() && !battleField.enemyTeamWins()) {
            List<Battler> actionCandidates = updateActionPoint();
            sortActionCandidates(actionCandidates);

            actionCandidates.forEach(battler -> {
                battleHelper.notifyAllBattlers(buildActionStartEvent(battleField, battler));
                processAction(battler);
                battleHelper.notifyAllBattlers(buildActionEndEvent(battleField, battler));
            });
        }
    }

    private List<Battler> updateActionPoint() {
        List<Battler> actionCandidates = battleField.getLiveBattlers().stream().filter(battler ->
                getUpdatedActionPoint(battler) >= battler.getActionPoint().getMax()).collect(Collectors.toList());

        battleField.getLiveBattlers().forEach(battler -> {
            int actionPoint = getUpdatedActionPoint(battler);
            actionPoint %= battler.getActionPoint().getMax();
            battler.getActionPoint().setValue(actionPoint);
        });

        return actionCandidates;
    }

    private int getUpdatedActionPoint(Battler battler) {
        return battler.getActionPoint().getValue() + battler.getSpeed();
    }

    private void sortActionCandidates(List<Battler> actionCandidates) {
        Collections.sort(actionCandidates, Comparator.comparingDouble((Battler battler) -> (double) battler
                .getActionPoint().getValue() / (double) battler.getSpeed()).reversed());
    }

    private void processAction(Battler battler) {
        Choices choices = battler.getController().choose(buildActionChoices(battler));
        Action action = Action.valueOf(choices.getChoices().get(choices.getChosenIndex()));

        switch (action) {
            case SUMMON_ELEMENT:
                break;
            case ABSORB_ELEMENT:
                break;
            case PHYSICAL_ATTACK:
                break;
            default:
                throw new RuntimeException("Unknown action type.");
        }
    }

    private Event buildBattleStartEvent(BattleField battleField) {
        Event result = new Event(EventType.BATTLE_START);
        result.putAttribute("battleField", battleField);
        return result;
    }

    private Event buildActionStartEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_START);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    private Event buildActionEndEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_END);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    private Choices buildActionChoices(Battler battler) {
        if (battler.isMagician()) {
            return new Choices(ChoicesPurpose.BATTLE_ACTION, Arrays.asList( //
                    Action.SUMMON_ELEMENT.name(), //
                    Action.ABSORB_ELEMENT.name()));
        } else {
            return new Choices(ChoicesPurpose.BATTLE_ACTION, Collections.singletonList(Action.PHYSICAL_ATTACK.name()));
        }
    }
}
