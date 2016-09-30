package xigua.battle.of.elements.logic.battle.actionprocessor;

import xigua.battle.of.elements.logic.battle.MagicBuilder;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.stream.Collectors;

public final class MagicEffectHelper {
    private static final double ATTACK_LEVEL_FACTOR = 0.4;
    private static final double DEFENCE_LEVEL_FACTOR = 0.4;
    private static final double MINIMAL_HIT_POINT_DELTA = 1;

    protected static MagicEffectProcessor getAttackProcessor() {
        return (magic, attacker, defender) -> {
            processCommonAttack(magic, attacker, defender);

            if (magic.getType() != Element.NONE) {
                processMagicalAttackDebuff(magic, defender);
            } else {
                processPhysicalAttackReflection(attacker, defender);
            }
        };
    }

    protected static MagicEffectProcessor getDefendProcessor() {
        return (magic, defender, defendant) -> {
        };
    }

    protected static MagicEffectProcessor getHealProcessor() {
        return (magic, healer, patient) -> {
        };
    }

    private static void processCommonAttack(Magic magic, Battler attacker, Battler defender) {
        Element type = magic.getType();
        int attackLevel = magic.getPrimaryLevel();
        int attack = attacker.getAttack();
        int defence = defender.getDefence();
        int defenceLevel = defender.getStates().stream()
                // Filter defending buff:
                .filter(state -> state.getUsage() == ElementUsage.DEFEND && state.getType().getConstructedElement()
                        == type)
                // Count total defending buff level:
                .collect(Collectors.summingInt(Magic::getSecondaryLevel));

        for (Magic state : defender.getStates()) {
            if (state.getUsage() != ElementUsage.DEFEND || state.getType().getConstructedElement() != type || state
                    .getPrimaryLevel() == 0) {
                continue;
            }

            attackLevel -= state.getPrimaryLevel();

            if (attackLevel <= 0) {
                state.setPrimaryLevel(-attackLevel);
                return;
            }

            state.setPrimaryLevel(0);
        }

        int hitPointDelta = (int) Math.round(Math.max(MINIMAL_HIT_POINT_DELTA, ((double) attack) * (1.0 +
                ATTACK_LEVEL_FACTOR * ((double) attackLevel)) - ((double) defence) * (1.0 + DEFENCE_LEVEL_FACTOR * (
                        (double) defenceLevel))));

        defender.getHitPoint().setValue(defender.getHitPoint().getValue() - hitPointDelta);
    }

    private static void processMagicalAttackDebuff(Magic magic, Battler defender) {
        defender.getStates().add(MagicBuilder.buildDebuffState(magic.getType(), magic.getSecondaryLevel()));
    }

    private static void processPhysicalAttackReflection(Battler attacker, Battler defender) {
        defender.getStates().stream()
                // All active defending magic:
                .filter(state -> state.getUsage() == ElementUsage.DEFEND || state.getSecondaryLevel() != 0)
                // Treat them as poison debuffs:
                .forEach(state -> {
                    processSingleState(MagicBuilder.buildDebuffState(state.getType(), state
                            .getSecondaryLevel()), attacker);
                });
    }

    private static void processSingleState(Magic state, Battler target) {

    }

    protected interface MagicEffectProcessor {
        void process(Magic magic, Battler caster, Battler target);
    }
}
