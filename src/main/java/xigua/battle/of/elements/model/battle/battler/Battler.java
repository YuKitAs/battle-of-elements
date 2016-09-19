package xigua.battle.of.elements.model.battle.battler;

import xigua.battle.of.elements.model.IntWithMax;
import xigua.battle.of.elements.model.battle.FreeElementBank;

import java.lang.reflect.Field;

public class Battler {
    private final BasicFacts basicFacts;
    private final InBattleStatus inBattleStatus;

    private Battler(BasicFacts basicFacts, InBattleStatus inBattleStatus) {
        this.basicFacts = basicFacts;
        this.inBattleStatus = inBattleStatus;
    }

    public BasicFacts getBasicFacts() {
        return basicFacts;
    }

    public InBattleStatus getInBattleStatus() {
        return inBattleStatus;
    }

    public static class Builder {
        private String name = null;
        private Boolean isFriendly = null;

        private Boolean isMagician = null;

        private IntWithMax hitPoint = null;
        private IntWithMax magicPoint = null;

        private Integer attack = null;
        private Integer defence = null;
        private Integer speed = null;

        private Integer summonedElementBankSize = null;
        private FreeElementBank freeElementBank = null;

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

        public Builder withMagicPoint(int current, int max) {
            this.magicPoint = new IntWithMax(current, max);
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

        public Battler build() {
            verifyFields();

            BasicFacts basicFacts = new BasicFacts(name, isFriendly, isMagician, attack, defence, speed);
            InBattleStatus inBattleStatus = new InBattleStatus(hitPoint, magicPoint, summonedElementBankSize,
                    freeElementBank);

            return new Battler(basicFacts, inBattleStatus);
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
