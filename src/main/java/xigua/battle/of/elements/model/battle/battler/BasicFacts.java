package xigua.battle.of.elements.model.battle.battler;

public class BasicFacts {
    private final String name;

    private final boolean isFriendly;

    private final boolean isMagician;

    private final int attack;
    private final int defence;
    private final int speed;


    public BasicFacts(String name, boolean isFriendly, boolean isMagician, int attack, int defence, int speed) {
        this.name = name;
        this.isFriendly = isFriendly;
        this.isMagician = isMagician;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
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
}
