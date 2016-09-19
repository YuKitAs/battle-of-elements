package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.List;

public class Battle {
    private final List<Battler> battlers;
    private final ElementFactory elementFactory;

    public Battle(List<Battler> battlers, ElementFactory elementFactory) {
        this.battlers = battlers;
        this.elementFactory = elementFactory;
    }

    public void start() {
        while (battlers.stream().filter(battler -> battler.getBasicFacts().isFriendly()).count() == 0 || battlers
                .stream().filter(battler -> !battler.getBasicFacts().isFriendly()).count() == 0) {

        }
    }

    private void turn(Battler battler) {

    }
}
