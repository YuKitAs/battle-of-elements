package xigua.battle.of.elements.model.battle;

import xigua.battle.of.elements.model.battle.battler.Battler;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BattleField implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Set<Battler> battlers = new HashSet<>();
    private final Environment environment;

    public BattleField(Set<Battler> battlers, Environment environment) {
        battlers.forEach(this::addBattler);
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Set<Battler> getAllBattlers() {
        return Collections.unmodifiableSet(battlers);
    }

    public Set<Battler> getLiveBattlers() {
        return battlers.stream().filter(battler -> !battler.isDead()).collect(Collectors.toSet());
    }

    public int getFriendlyBattlerNumber() {
        return (int) getLiveBattlers().stream().filter(Battler::isFriendly).count();
    }

    public int getEnemyBattlerNumber() {
        return (int) getLiveBattlers().stream().filter(battler -> !battler.isFriendly()).count();
    }

    public Set<Battler> getFriendsFor(Battler battler) {
        return getLiveBattlers().stream().filter(possibleEnemy -> possibleEnemy.isFriendly() == battler.isFriendly())
                .collect(Collectors.toSet());
    }

    public Set<Battler> getEnemiesFor(Battler battler) {
        return getLiveBattlers().stream().filter(possibleEnemy -> possibleEnemy.isFriendly() != battler.isFriendly())
                .collect(Collectors.toSet());
    }

    public Battler getBattler(String name) {
        return getLiveBattlers().stream().filter(battler -> battler.getName().equals(name)).findFirst().orElse(null);
    }

    public void addBattler(Battler battler) {
        battlers.add(battler);
    }
}
