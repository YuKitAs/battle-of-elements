package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

final class MagicEffectHelper {
    protected static MagicEffectProcessor getAttackProcessor() {
        return (magic, attacker, defender) -> {
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

    protected interface MagicEffectProcessor {
        void process(Magic magic, Battler caster, Battler target);
    }
}
