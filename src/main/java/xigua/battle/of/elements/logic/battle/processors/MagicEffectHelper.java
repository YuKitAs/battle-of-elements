package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

abstract class MagicEffectProcessor {
    protected abstract void process(Magic magic, Battler caster, Battler victim);

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
}
