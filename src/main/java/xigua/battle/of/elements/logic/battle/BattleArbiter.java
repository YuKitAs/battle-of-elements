package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.ChoicesBuilder;
import xigua.battle.of.elements.logic.EventBuilder;
import xigua.battle.of.elements.logic.battle.actionprocessor.ActionProcessor;
import xigua.battle.of.elements.logic.battle.actionprocessor.SummonElementActionProcessor;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.Action;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BattleArbiter {
    private final BattleField battleField;
    private final Map<Action, ActionProcessor> actionProcessorMap = new HashMap<>();

    public BattleArbiter(ElementFactory elementFactory, Set<Battler> battlers) {
        battleField = new BattleField(battlers);

        actionProcessorMap.put(Action.SUMMON_ELEMENT, new SummonElementActionProcessor(elementFactory));
    }

    public void start() {
        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildBattleStartedEvent(battleField));

        while (battleField.getFriendlyBattlerNumber() != 0 && battleField.getEnemyBattlerNumber() != 0) {
            List<Battler> actionCandidates = updateActionPoint();

            sortActionCandidates(actionCandidates);

            actionCandidates.forEach(battler -> {
                BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildActionStartedEvent(battleField, battler));
                processAction(battler);
                BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildActionEndedEvent(battleField, battler));
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
        Choices choices = battler.getController().choose(ChoicesBuilder.buildActionChoices(battler));
        Action action = Action.valueOf(choices.getChoices().get(choices.getChosenIndex()));

        actionProcessorMap.get(action).process(battleField, battler);
    }
}
