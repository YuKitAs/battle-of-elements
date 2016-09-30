package xigua.battle.of.elements.logic.battle.actionprocessor;

import xigua.battle.of.elements.logic.ChoicesBuilder;
import xigua.battle.of.elements.logic.EventBuilder;
import xigua.battle.of.elements.logic.battle.BattleHelper;
import xigua.battle.of.elements.logic.battle.ElementFactory;
import xigua.battle.of.elements.logic.battle.MagicBuilder;
import xigua.battle.of.elements.model.ChoicePurpose;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.List;

public class SummonElementActionProcessor implements ActionProcessor {
    private final ElementFactory elementFactory;

    public SummonElementActionProcessor(ElementFactory elementFactory) {
        this.elementFactory = elementFactory;
    }

    @Override
    public void process(BattleField battleField, Battler battler) {
        List<Element> generatedElements = ElementSelectionHelper.generateElements(elementFactory);

        Choices elementSummonChoices = ChoicesBuilder.buildElementSelectionChoices(ChoicePurpose
                .BATTLE_SUMMON_ELEMENT, generatedElements, battler.getFreeElementBank());

        Choices elementSummonResult = battler.getController().choose(elementSummonChoices);

        Element summonedElement = ElementSelectionHelper.getSelectedElement(elementSummonResult, generatedElements,
                battler.getFreeElementBank());

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildElementSummonedEvent(battleField, battler,
                summonedElement));

        battler.getSummonedElementBank().add(summonedElement);

        if (!MagicBuilder.canBuildMagic(battler.getSummonedElementBank())) {
            BattleHelper.notifyAllBattlers(battleField, EventBuilder.buileMagicNotCastedEvent(battleField, battler));
            return;
        }

        Magic magic = MagicBuilder.buildFromSummonedElementBank(battler.getSummonedElementBank());

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildMagicCastedEvent(battleField, battler, magic));

        if (magic.isEmpty()) {
            return;
        }
    }

    /***************************************************************************************************************

     switch (magic.getUsage()) {
     case ATTACK:
     processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     .BATTLE_ATTACK_TARGET_CHOSEN), MagicEffectHelper.getAttackProcessor(), AfterMagicEventBuilder
     .with(EventType.BATTLE_AFTER_ATTACK));
     break;
     case DEFEND:
     processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     .BATTLE_DEFEND_TARGET_CHOSEN), MagicEffectHelper.getDefendProcessor(), AfterMagicEventBuilder
     .with(EventType.BATTLE_AFTER_DEFEND));
     break;
     case HEAL:
     processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     .BATTLE_HEAL_TARGET_CHOSEN), MagicEffectHelper.getHealProcessor(), AfterMagicEventBuilder
     .with(EventType.BATTLE_AFTER_HEAL));
     break;
     default:
     throw new RuntimeException("Cannot process magic usage.");
     }
     }

     private void processMagic(Magic magic, Set<Battler> targets, MagicTargetChosenEventBuilder
     magicTargetChosenEventBuilder, MagicEffectProcessor processor, AfterMagicEventBuilder
     afterMagicEventBuilder) {
     Choices targetSelectionChoices = TargetSelectionHelper.buildTargetSelectionChoices(targets);

     Choices targetSelectionResult = battler.getController().choose(targetSelectionChoices);

     Battler target = battleField.getBattler(targetSelectionResult.getChosenItem());

     BattleHelper.notifyAllBattlers(battleField, magicTargetChosenEventBuilder.build(battleField, battler, target));

     Battler battlerBefore = DeepCopy.copy(battler);
     Battler targetBefore = DeepCopy.copy(target);

     processor.process(magic, battler, target);

     BattleHelper.notifyAllBattlers(battleField, afterMagicEventBuilder.build(battleField, battler, battlerBefore,
     target, targetBefore));
     }
     */

    private interface MagicTargetChosenEventBuilder {
        Event build(BattleField battleField, Battler battlerInTurn, Battler target);

        static MagicTargetChosenEventBuilder with(EventType type) {
            return (battleField, battlerInTurn, target) -> {
                Event result = new Event(type);
                result.putAttribute("battleField", battleField);
                result.putAttribute("battlerInTurn", battlerInTurn);
                result.putAttribute("target", target);
                return result;
            };
        }
    }

    private interface AfterMagicEventBuilder {
        Event build(BattleField battleField, Battler battlerInTurn, Battler battlerBeforeAttack, Battler target,
                Battler targetBeforeAttack);

        static AfterMagicEventBuilder with(EventType type) {
            return (battleField, battlerInTurn, battlerBeforeAttack, target, targetBeforeAttack) -> {
                Event result = new Event(type);
                result.putAttribute("battleField", battleField);
                result.putAttribute("battlerInTurn", battlerInTurn);
                result.putAttribute("battlerBefore", battlerBeforeAttack);
                result.putAttribute("target", target);
                result.putAttribute("targetBefore", targetBeforeAttack);
                return result;
            };
        }
    }
}
