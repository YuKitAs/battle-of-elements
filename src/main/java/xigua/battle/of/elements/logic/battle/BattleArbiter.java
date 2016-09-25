package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.logic.battle.actions.Action;
import xigua.battle.of.elements.logic.battle.actions.MagicBuilder;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Environment;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.utility.DeepCopy;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BattleArbiter {
    private final BattleField battleField;
    private final ElementFactory elementFactory;
    private final Map<Battler, BattlerController> battlerControllerMap = new HashMap<>();
    private final Map<Battler, BattlerObserver> battlerObserverMap = new HashMap<>();
    private final MagicBuilder magicBuilder;

    public BattleArbiter(Environment environment, List<Battler> battlers) {
        battleField = new BattleField(battlers, environment);
        elementFactory = new ElementFactory(environment);
        magicBuilder = new MagicBuilder();
    }

    public void addBattlerController(Battler battler, BattlerController battlerController) {
        battlerControllerMap.put(battler, battlerController);
    }

    public void addBattlerObserver(Battler battler, BattlerObserver battlerObserver) {
        battlerObserverMap.put(battler, battlerObserver);
    }

    public void start() {
        battlerObserverMap.entrySet().forEach(entry -> entry.getValue().notifyBattleStarted(entry.getKey(),
                battleField));

        while (!battleField.friendTeamWins() && !battleField.enemyTeamWins()) {
            List<Battler> actionCandidates = updateActionPoint();
            sortActionCandidates(actionCandidates);
            actionCandidates.forEach(battler -> {
                if (battlerObserverMap.containsKey(battler)) {
                    battlerObserverMap.get(battler).notifyRoundStarted(DeepCopy.copy(battler), DeepCopy.copy
                            (battleField));
                }

                processRound(battler);

                if (battlerObserverMap.containsKey(battler)) {
                    battlerObserverMap.get(battler).notifyRoundEnded(DeepCopy.copy(battler), DeepCopy.copy
                            (battleField));
                }
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

    private void sortActionCandidates(List<Battler> actionCandidates) {
        Collections.sort(actionCandidates, Comparator.comparingDouble((Battler battler) -> (double) battler
                .getActionPoint().getValue() / (double) battler.getSpeed()).reversed());
    }

    private void processRound(Battler battler) {
        BattlerController battlerController = battlerControllerMap.get(battler);

        Action action = battlerController.chooseAction(getActionChoices(battler));

        switch (action) {
            case SUMMON_ELEMENT:
                processSummonElementAction(battler, battlerController);
                break;
            case PHYSICAL_ATTACK:
            case ABSORB_ELEMENT:
            default:
                // Currently do nothing.
        }
    }

    private void processSummonElementAction(Battler battler, BattlerController battlerController) {

    }

    private Set<Action> getActionChoices(Battler battler) {
        if (battler.isMagician()) {
            return Stream.of(Action.SUMMON_ELEMENT, Action.ABSORB_ELEMENT).collect(Collectors.toSet());
        } else {
            return Collections.singleton(Action.PHYSICAL_ATTACK);
        }
    }

    private int getUpdatedActionPoint(Battler battler) {
        return battler.getActionPoint().getValue() + battler.getSpeed();
    }
}
