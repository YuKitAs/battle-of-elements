package xigua.battle.of.elements.logic.battle.actionprocessor;

import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.ArrayList;
import java.util.List;

public class DefendMagicProcessor extends AbstractMagicProcessor {
    @Override
    protected List<Battler> getTargets(BattleField battleField, Battler caster) {
        return new ArrayList<>(battleField.getFriendsFor(caster));
    }

    @Override
    protected void processMagic(Magic magic, Battler battlerInTurn, Battler magicTarget) {

    }
}
