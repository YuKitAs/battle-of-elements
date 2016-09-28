package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.battle.processors.EscapeFromBattleActionProcessor;
import xigua.battle.of.elements.logic.battle.processors.PhysicalAttackActionProcessor;
import xigua.battle.of.elements.logic.battle.processors.SummonElementActionProcessor;
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
    private final ElementFactory elementFactory;

    public BattleArbiter(Environment environment, Set<Battler> battlers) {
        battleField = new BattleField(battlers, environment);
        elementFactory = new ElementFactory(environment);
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
        List<Battler> actionCandidates = battleField.getLiveBattlers().stream().filter(battler -> getUpdatedActionPoint(battler) >= battler.getActionPoint().getMaxValue()).collect(Collectors.toList());

        battleField.getLiveBattlers().forEach(battler -> {
            int actionPoint = getUpdatedActionPoint(battler);
            actionPoint %= battler.getActionPoint().getMaxValue();
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
                new SummonElementActionProcessor(battler, battleField, elementFactory).process();
                break;
            case PHYSICAL_ATTACK:
                new PhysicalAttackActionProcessor(battler, battleField).process();
                break;
            case ESCAPE_FROM_BATTLE:
                new EscapeFromBattleActionProcessor(battler, battleField).process();
                break;
            default:
                throw new RuntimeException("Unknown action type.");
        }
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
            return new Choices(ChoicesPurpose.BATTLE_ACTION, Arrays.asList(Action.SUMMON_ELEMENT.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        } else {
            return new Choices(ChoicesPurpose.BATTLE_ACTION, Arrays.asList(Action.PHYSICAL_ATTACK.name(), Action
                    .ESCAPE_FROM_BATTLE.name()));
        }
    }
}
