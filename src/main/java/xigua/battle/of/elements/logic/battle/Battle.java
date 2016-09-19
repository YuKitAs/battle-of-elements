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

    }
}
