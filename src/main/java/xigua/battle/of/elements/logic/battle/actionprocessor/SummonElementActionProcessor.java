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
import xigua.battle.of.elements.model.battle.ElementUsage;
import xigua.battle.of.elements.model.battle.FreeElementBank;
import xigua.battle.of.elements.model.battle.Magic;
import xigua.battle.of.elements.model.battle.SummonedElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummonElementActionProcessor implements ActionProcessor {
    private static final int NUM_OF_ELEMENTS_PER_ACTION = 2;

    private final ElementFactory elementFactory;
    private final Map<ElementUsage, Class<? extends MagicProcessor>> magicTypeProcessorClassMap = new HashMap<>();

    public SummonElementActionProcessor(ElementFactory elementFactory) {
        this.elementFactory = elementFactory;

        magicTypeProcessorClassMap.put(ElementUsage.ATTACK, AttackMagicProcessor.class);
        magicTypeProcessorClassMap.put(ElementUsage.DEFEND, DefendMagicProcessor.class);
        magicTypeProcessorClassMap.put(ElementUsage.HEAL, HealMagicProcessor.class);
    }

    @Override
    public void process(BattleField battleField, Battler battler) {
        List<Element> restElements = summonElement(battleField, battler);

        castMagic(battleField, battler);
    }

    private List<Element> summonElement(BattleField battleField, Battler battler) {
        List<Element> generatedElements = generateElements();

        Element summonedElement = chooseElement(battler, generatedElements);

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildElementSummonedEvent(battleField, battler,
                summonedElement));

        if (summonedElement == null) {
            return generatedElements;
        }

        if (battler.getSummonedElementBank().getCurrentSize() == battler.getSummonedElementBank().getMaxSize()) {
            abandonElement(battler);
        }

        battler.getSummonedElementBank().add(summonedElement);

        return generatedElements;
    }

    private void castMagic(BattleField battleField, Battler battler) {
        if (!MagicBuilder.canBuildMagic(battler.getSummonedElementBank())) {
            BattleHelper.notifyAllBattlers(battleField, EventBuilder.buileMagicNotCastedEvent(battleField, battler));
            return;
        }

        Magic magic = MagicBuilder.buildFromSummonedElementBank(battler.getSummonedElementBank());

        BattleHelper.notifyAllBattlers(battleField, EventBuilder.buildMagicCastedEvent(battleField, battler, magic));

        battler.getSummonedElementBank().clear();

        if (magic.isEmpty()) {
            return;
        }
    }

    private Element chooseElement(Battler battler, List<Element> generatedElements) {
        Choices elementSummonChoices = ChoicesBuilder.buildElementSelectionChoices(ChoicePurpose
                .BATTLE_SUMMON_ELEMENT, generatedElements, battler.getFreeElementBank(), new SummonedElementBank(0));
        Choices elementSummonResult = battler.getController().choose(elementSummonChoices);

        return getSelectedElement(elementSummonResult.getChosenIndex(), generatedElements, battler.getFreeElementBank
                (), new SummonedElementBank(0));
    }

    private void abandonElement(Battler battler) {
        Choices elementAbandonChoices = ChoicesBuilder.buildElementSelectionChoices(ChoicePurpose
                .BATTLE_ABANDON_ELEMENT, Collections.emptyList(), new FreeElementBank(0), battler
                .getSummonedElementBank());
        Choices elementAbandonResult = battler.getController().choose(elementAbandonChoices);

        getSelectedElement(elementAbandonResult.getChosenIndex(), Collections.emptyList(), new FreeElementBank(0),
                battler.getSummonedElementBank());
    }

    private List<Element> generateElements() {
        List<Element> generatedElements = new ArrayList<>();
        for (int i = 0; i < NUM_OF_ELEMENTS_PER_ACTION; i++) {
            generatedElements.add(elementFactory.getElement());
        }

        return generatedElements;
    }

    private static Element getSelectedElement(int index, List<Element> generatedElements, FreeElementBank
            freeElementBank, SummonedElementBank summonedElementBank) {
        Element summonedElement;

        if (index < generatedElements.size()) {
            summonedElement = generatedElements.remove(index);
        } else if (index < generatedElements.size() + freeElementBank.getCurrentSize()) {
            summonedElement = freeElementBank.toDistinctList().get(index - generatedElements.size());
            freeElementBank.removeElement(summonedElement);
        } else if (index < generatedElements.size() + freeElementBank.getCurrentSize() + summonedElementBank
                .getCurrentSize()) {
            summonedElement = summonedElementBank.toList().get(index - generatedElements.size() - freeElementBank
                    .getCurrentSize());
            summonedElementBank.remove(index - generatedElements.size() - freeElementBank.getCurrentSize());
        } else {
            summonedElement = null;
        }

        return summonedElement;
    }

    /***************************************************************************************************************
     * switch (magic.getUsage()) {
     * case ATTACK:
     * processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     * .BATTLE_ATTACK_TARGET_CHOSEN), MagicEffectHelper.getAttackProcessor(), AfterMagicEventBuilder
     * .with(EventType.BATTLE_AFTER_ATTACK));
     * break;
     * case DEFEND:
     * processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     * .BATTLE_DEFEND_TARGET_CHOSEN), MagicEffectHelper.getDefendProcessor(), AfterMagicEventBuilder
     * .with(EventType.BATTLE_AFTER_DEFEND));
     * break;
     * case HEAL:
     * processMagic(magic, battleField.getEnemiesFor(battler), MagicTargetChosenEventBuilder.with(EventType
     * .BATTLE_HEAL_TARGET_CHOSEN), MagicEffectHelper.getHealProcessor(), AfterMagicEventBuilder
     * .with(EventType.BATTLE_AFTER_HEAL));
     * break;
     * default:
     * throw new RuntimeException("Cannot process magic usage.");
     * }
     * }
     * <p>
     * private void processMagic(Magic magic, Set<Battler> targets, MagicTargetChosenEventBuilder
     * magicTargetChosenEventBuilder, MagicEffectProcessor processor, AfterMagicEventBuilder
     * afterMagicEventBuilder) {
     * Choices targetSelectionChoices = TargetSelectionHelper.buildTargetSelectionChoices(targets);
     * <p>
     * Choices targetSelectionResult = battler.getController().choose(targetSelectionChoices);
     * <p>
     * Battler target = battleField.getBattler(targetSelectionResult.getChosenItem());
     * <p>
     * BattleHelper.notifyAllBattlers(battleField, magicTargetChosenEventBuilder.build(battleField, battler, target));
     * <p>
     * Battler battlerBefore = DeepCopy.copy(battler);
     * Battler targetBefore = DeepCopy.copy(target);
     * <p>
     * processor.process(magic, battler, target);
     * <p>
     * BattleHelper.notifyAllBattlers(battleField, afterMagicEventBuilder.build(battleField, battler, battlerBefore,
     * target, targetBefore));
     * }
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
