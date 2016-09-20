package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Battle {
    private final List<Battler> battlers;
    private final ElementFactory elementFactory;

    public Battle(List<Battler> battlers, ElementFactory elementFactory) {
        this.battlers = battlers;
        this.elementFactory = elementFactory;
    }

    public boolean start() {
        while (!friendTeamWins() && !enemyTeamWins()) {
            List<Battler> actionCandidates = updateActionPoint();
            sortActionCandidates(actionCandidates);

            actionCandidates.forEach(this::processTurn);
        }

        return friendTeamWins();
    }

    private boolean enemyTeamWins() {
        return getLiveBattlers().stream().filter(Battler::isFriendly).count() == 0L;
    }

    private boolean friendTeamWins() {
        return getLiveBattlers().stream().filter(battler -> !battler.isFriendly()).count() == 0L;
    }

    private List<Battler> updateActionPoint() {
        List<Battler> actionCandidates = getLiveBattlers().stream().filter(battler -> getUpdatedActionPoint(battler)
                > battler.getActionPoint().getMax()).collect(Collectors.toList());

        getLiveBattlers().forEach(battler -> {
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

    private void processTurn(Battler battler) {

    }

    private List<Battler> getLiveBattlers() {
        return battlers.stream().filter(battler -> !battler.isDead()).collect(Collectors.toList());
    }

    private int getUpdatedActionPoint(Battler battler) {
        return battler.getActionPoint().getValue() + battler.getSpeed();
    }
}
