package xigua.battle.of.elements.model.battle;

import xigua.battle.of.elements.model.IntWithMax;

import java.util.ArrayList;
import java.util.List;

public class Battler {
    private final boolean isMagician;
    private final IntWithMax hitPoint;
    private final IntWithMax magicPoint;

    private final int attack;
    private final int defence;
    private final int speed;

    private final List<State> states;
    private final SummonedElementBank summonedElementBank;
    private final FreeElementBank freeElementBank;

    private Battler(boolean isMagician, IntWithMax hitPoint, IntWithMax magicPoint, int attack, int defence, int
            speed, int summonedElementBankSize, FreeElementBank freeElementBank) {
        this.isMagician = isMagician;
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.states = new ArrayList<>();
        this.summonedElementBank = new SummonedElementBank(summonedElementBankSize);
        this.freeElementBank = freeElementBank;
    }

    public boolean isMagician() {
        return isMagician;
    }

    public IntWithMax getHitPoint() {
        return hitPoint;
    }

    public IntWithMax getMagicPoint() {
        return magicPoint;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
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

    public static class Builder {
        private Boolean isMagician = null;
        private IntWithMax hitPoint = null;
        private IntWithMax magicPoint = null;

        private Integer attack = null;
        private Integer defence = null;
        private Integer speed = null;

        private Integer summonedElementBankSize = null;
        private FreeElementBank freeElementBank = null;

        public Builder isMagician(boolean magician) {
            isMagician = magician;
            return this;
        }

        public Builder withHitPoint(IntWithMax hitPoint) {
            this.hitPoint = hitPoint;
            return this;
        }

        public Builder withMagicPoint(IntWithMax magicPoint) {
            this.magicPoint = magicPoint;
            return this;
        }

        public Builder withAttack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder withDefence(int defence) {
            this.defence = defence;
            return this;
        }

        public Builder withSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public Builder withSummonedElementBankSize(Integer summonedElementBankSize) {
            this.summonedElementBankSize = summonedElementBankSize;
            return this;
        }

        public Builder withFreeElementBank(FreeElementBank freeElementBank) {
            this.freeElementBank = freeElementBank;
            return this;
        }

        public Battler build() {
            if (isMagician == null || hitPoint == null || magicPoint == null || attack == null || defence == null ||
                    speed == null || summonedElementBankSize == null || freeElementBank == null) {
                throw new NullPointerException("One or more attributes have null value.");
            }

            return new Battler(isMagician, hitPoint, magicPoint, attack, defence, speed, summonedElementBankSize,
                    freeElementBank);
        }
    }
}
