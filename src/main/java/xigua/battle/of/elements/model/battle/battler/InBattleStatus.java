package xigua.battle.of.elements.model.battle.battler;

import xigua.battle.of.elements.model.IntWithMax;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.State;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import java.util.LinkedList;
import java.util.List;

public class InBattleStatus {
    private boolean isDead = false;

    private final IntWithMax hitPoint;
    private final IntWithMax magicPoint;
    private final IntWithMax actionPoint = new IntWithMax(0, 100);

    private final List<State> states;
    private final SummonedElementBank summonedElementBank;
    private final FreeElementBank freeElementBank;

    protected InBattleStatus(IntWithMax hitPoint, IntWithMax magicPoint, int summonedElementBankSize, FreeElementBank
            freeElementBank) {
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;

        this.states = new LinkedList<>();
        this.summonedElementBank = new SummonedElementBank(summonedElementBankSize);
        this.freeElementBank = freeElementBank;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public IntWithMax getHitPoint() {
        return hitPoint;
    }

    public IntWithMax getMagicPoint() {
        return magicPoint;
    }

    public IntWithMax getActionPoint() {
        return actionPoint;
    }

    public List<State> getStates() {
        return states;
    }

    public SummonedElementBank getSummonedElementBank() {
        return summonedElementBank;
    }

    public FreeElementBank getFreeElementBank() {
        return freeElementBank;
    }
}
