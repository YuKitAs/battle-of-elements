package xigua.battle.of.elements.model.battle.battler;

import xigua.battle.of.elements.logic.battle.BattlerController;
import xigua.battle.of.elements.logic.battle.BattlerObserver;
import xigua.battle.of.elements.model.IntWithMax;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class Battler implements Serializable {
    private static final long serialVersionUID = 1L;

    // Basic facts:
    private final String name;
    private final boolean isFriendly;
    private final boolean isMagician;

    private final int attack;
    private final int defence;
    private final int speed;

    // In battle status:
    private boolean isDead = false;

    private final IntWithMax hitPoint;
    private final IntWithMax actionPoint = new IntWithMax(0, 100);

    private final List<Magic> states;
    private final SummonedElementBank summonedElementBank;
    private final FreeElementBank freeElementBank;

    private final BattlerController controller;
    private final BattlerObserver observer;

    public Battler(String name, boolean isFriendly, boolean isMagician, int attack, int defence, int speed,
            IntWithMax hitPoint, int summonedElementBankSize, FreeElementBank freeElementBank, BattlerController
            controller, BattlerObserver observer) {
        this.name = name;
        this.isFriendly = isFriendly;
        this.isMagician = isMagician;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;

        this.hitPoint = hitPoint;

        this.states = new LinkedList<>();
        this.summonedElementBank = new SummonedElementBank(summonedElementBankSize);
        this.freeElementBank = freeElementBank;

        this.controller = controller;
        controller.setBattler(this);

        this.observer = observer;
        observer.setBattler(this);
    }

    public String getName() {
        return name;
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    public boolean isMagician() {
        return isMagician;
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public IntWithMax getHitPoint() {
        return hitPoint;
    }

    public IntWithMax getActionPoint() {
        return actionPoint;
    }

    public List<Magic> getStates() {
        return states;
    }

    public SummonedElementBank getSummonedElementBank() {
        return summonedElementBank;
    }

    public FreeElementBank getFreeElementBank() {
        return freeElementBank;
    }

    public BattlerController getController() {
        return controller;
    }

    public BattlerObserver getObserver() {
        return observer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Battler)) return false;

        Battler battler = (Battler) o;

        return name.equals(battler.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static class Builder {
        private String name = null;
        private Boolean isFriendly = null;

        private Boolean isMagician = null;

        private IntWithMax hitPoint = null;

        private Integer attack = null;
        private Integer defence = null;
        private Integer speed = null;

        private Integer summonedElementBankSize = null;
        private FreeElementBank freeElementBank = null;

        private BattlerController controller = null;
        private BattlerObserver observer = null;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder isFriendly(boolean friendly) {
            isFriendly = friendly;
            return this;
        }

        public Builder isMagician(boolean magician) {
            isMagician = magician;
            return this;
        }

        public Builder withHitPoint(int current, int max) {
            this.hitPoint = new IntWithMax(current, max);
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

        public Builder withSummonedElementBankSize(int summonedElementBankSize) {
            this.summonedElementBankSize = summonedElementBankSize;
            return this;
        }

        public Builder withFreeElementBank(FreeElementBank freeElementBank) {
            this.freeElementBank = freeElementBank;
            return this;
        }

        public Builder withController(BattlerController controller) {
            this.controller = controller;
            return this;
        }

        public Builder withObserver(BattlerObserver observer) {
            this.observer = observer;
            return this;
        }

        public Battler build() {
            verifyFields();

            return new Battler(name, isFriendly, isMagician, attack, defence, speed, hitPoint,
                    summonedElementBankSize, freeElementBank, controller, observer);
        }

        private void verifyFields() {
            for (Field field : getClass().getDeclaredFields()) {
                try {
                    if (field.get(this) == null) {
                        throw new NullPointerException(String.format("The field %s has no value.", field.getName()));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot verify fields.", e);
                }
            }
        }
    }
}