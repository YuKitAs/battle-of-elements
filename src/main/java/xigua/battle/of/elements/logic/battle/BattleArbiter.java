package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.battle.actionprocessor.ActionProcessor;
import xigua.battle.of.elements.logic.battle.actionprocessor.SummonElementActionProcessor;
import xigua.battle.of.elements.model.ChoicePurpose;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.Action;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Environment;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BattleArbiter {
    private final BattleField battleField;
    private final ElementFactory elementFactory;
    private final Map<Action, ActionProcessor> actionProcessorMap = new HashMap<>();

    public BattleArbiter(Environment environment, Set<Battler> battlers) {
        battleField = new BattleField(battlers, environment);
        elementFactory = new ElementFactory(environment);

        actionProcessorMap.put(Action.SUMMON_ELEMENT, new SummonElementActionProcessor(new ElementFactory
                (environment)));
    }

    public void start() {
        BattleHelper.notifyAllBattlers(battleField, buildBattleStartedEvent(battleField));

        while (battleField.getFriendlyBattlerNumber() != 0 && battleField.getEnemyBattlerNumber() != 0) {
            List<Battler> actionCandidates = updateActionPoint();

            sortActionCandidates(actionCandidates);

            actionCandidates.forEach(battler -> {
                BattleHelper.notifyAllBattlers(battleField, buildActionStartedEvent(battleField, battler));
                processAction(battler);
                BattleHelper.notifyAllBattlers(battleField, buildActionEndedEvent(battleField, battler));
            });
        }
    }

    private List<Battler> updateActionPoint() {
        List<Battler> actionCandidates = battleField.getLiveBattlers().stream().filter(battler ->
                getUpdatedActionPoint(battler) >= battler.getActionPoint().getMaxValue()).collect(Collectors.toList());

        battleField.getLiveBattlers().forEach(battler -> {
            int actionPoint = getUpdatedActionPoint(battler);
            actionPoint %= battler.getActionPoint().getMaxValue();
            battler.getActionPoint().setValue(actionPoint);
        });

        return actionCandidates;
    }

    private void sortActionCandidates(List<Battler> actionCandidates) {
        Collections.sort(actionCandidates, Comparator.comparingDouble((Battler battler) -> ((double) battler.getSpeed
                () - (double) battler.getActionPoint().getValue()) / (double) battler.getSpeed()));
    }

    private int getUpdatedActionPoint(Battler battler) {
        return battler.getActionPoint().getValue() + battler.getSpeed();
    }

    private void processAction(Battler battler) {
        Choices choices = battler.getController().choose(buildActionChoices(battler));
        Action action = Action.valueOf(choices.getChoices().get(choices.getChosenIndex()));

        actionProcessorMap.get(action).process(battleField, battler);
    }

    private Event buildBattleStartedEvent(BattleField battleField) {
        Event result = new Event(EventType.BATTLE_STARTED);
        result.putAttribute("battleField", battleField);
        return result;
    }

    private Event buildActionStartedEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_STARTED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    private Event buildActionEndedEvent(BattleField battleField, Battler battlerInTurn) {
        Event result = new Event(EventType.BATTLE_ACTION_ENDED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        return result;
    }

    private Choices buildActionChoices(Battler battler) {
        if (battler.isMagician()) {
            return new Choices(ChoicePurpose.BATTLE_ACTION, Arrays.asList(Action.SUMMON_ELEMENT.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        } else {
            return new Choices(ChoicePurpose.BATTLE_ACTION, Arrays.asList(Action.PHYSICAL_ATTACK.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        }
    }
}
