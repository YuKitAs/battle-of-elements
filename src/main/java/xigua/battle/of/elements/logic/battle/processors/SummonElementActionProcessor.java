package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.logic.battle.BattleHelper;
import xigua.battle.of.elements.logic.battle.ElementFactory;
import xigua.battle.of.elements.logic.battle.MagicBuilder;
import xigua.battle.of.elements.logic.battle.processors.MagicEffectHelper.MagicEffectProcessor;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.ChoicesPurpose;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.utility.DeepCopy;

import java.util.List;
import java.util.Set;

public class SummonElementActionProcessor {

    private final Battler battler;
    private final BattleField battleField;
    private final ElementFactory elementFactory;

    public SummonElementActionProcessor(Battler battler, BattleField battleField, ElementFactory elementFactory) {
        this.battler = battler;
        this.battleField = battleField;
        this.elementFactory = elementFactory;
    }

    public void process() {
        List<Element> generatedElements = ElementSelectionHelper.generateElements(elementFactory);

        Choices elementSummonChoices = ElementSelectionHelper.buildElementSelectionChoices(ChoicesPurpose
                .BATTLE_SUMMON_ELEMENT, generatedElements, battler.getFreeElementBank());

        Choices elementSummonResult = battler.getController().choose(elementSummonChoices);

        Element summonedElement = ElementSelectionHelper.getSelectedElement(elementSummonResult, generatedElements,
                battler.getFreeElementBank());

        BattleHelper.notifyAllBattlers(battleField, buildElementSummonedEvent(battleField, battler, summonedElement));

        battler.getSummonedElementBank().add(summonedElement);

        Magic magic = MagicBuilder.fromSummonedElementBank(battler.getSummonedElementBank());

        BattleHelper.notifyAllBattlers(battleField, buildMagicCastedEvent(battleField, battler, magic));

        if (magic == Magic.EMPTY) {
            return;
        }

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

    private Event buildElementSummonedEvent(BattleField battleField, Battler battlerInTurn, Element summonedElement) {
        Event result = new Event(EventType.BATTLE_ELEMENT_SUMMONED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("summonedElement", summonedElement);
        return result;
    }

    private Event buildMagicCastedEvent(BattleField battleField, Battler battlerInTurn, Magic magicCasted) {
        Event result = new Event(EventType.BATTLE_MAGIC_CASTED);
        result.putAttribute("battleField", battleField);
        result.putAttribute("battlerInTurn", battlerInTurn);
        result.putAttribute("magicCasted", magicCasted);
        return result;
    }

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
