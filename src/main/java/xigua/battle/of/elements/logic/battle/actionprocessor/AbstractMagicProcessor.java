package xigua.battle.of.elements.logic.battle.actionprocessor;

import xigua.battle.of.elements.logic.ChoicesBuilder;
import xigua.battle.of.elements.logic.EventBuilder;
import xigua.battle.of.elements.logic.battle.BattleHelper;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.utility.DeepCopy;

import java.util.List;

public abstract class AbstractMagicProcessor implements MagicProcessor {
    @Override
    public void process(BattleField battleField, Battler battlerInTurn, Magic magic) {
        List<Battler> magicTargets = getTargets(battleField, battlerInTurn);
        Choices magicTargetChoices = ChoicesBuilder.buildMagicTargetChoices(magicTargets);
        Choices magicTargetResult = battlerInTurn.getController().choose(magicTargetChoices);
        Battler magicTarget = magicTargets.get(magicTargetResult.getChosenIndex());

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildMagicTargetChosenEvent(battleField,
                battlerInTurn, magic, magicTarget));

        Battler casterBefore = DeepCopy.copy(battlerInTurn);
        Battler targetBefore = DeepCopy.copy(magicTarget);

        processMagic(magic, battlerInTurn, magicTarget);

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildMagicProcessedEvent(battleField, battlerInTurn,
                casterBefore, magic, magicTarget, targetBefore));
    }

    protected abstract List<Battler> getTargets(BattleField battleField, Battler caster);

    protected abstract void processMagic(Magic magic, Battler battlerInTurn, Battler magicTarget);
}
