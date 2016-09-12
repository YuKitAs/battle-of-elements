package xigua.battle.of.elements.model.battle;

import xigua.battle.of.elements.model.IntWithMax;

import java.util.ArrayList;
import java.util.List;

public class Magician {
    private final IntWithMax hitPoint;
    private final IntWithMax magicPoint;
    private final IntWithMax staminaPoint;

    private final int attack;
    private final int defence;
    private final int speed;

    private final List<State> states;
    private final SummonedElementBank summonedElementBank;
    private final FreeElementBank freeElementBank;

    private Magician(IntWithMax hitPoint, IntWithMax magicPoint, IntWithMax staminaPoint, int attack, int defence, int
            speed, int summonedElementBankSize, FreeElementBank freeElementBank) {
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;
        this.staminaPoint = staminaPoint;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.states = new ArrayList<>();
        this.summonedElementBank = new SummonedElementBank(summonedElementBankSize);
        this.freeElementBank = freeElementBank;
    }
}
