package xigua.battle.of.elements.representation.cui;

import java.util.HashMap;
import java.util.Map;

import xigua.battle.of.elements.logic.battle.BattlerController;
import xigua.battle.of.elements.logic.battle.BattlerObserver;
import xigua.battle.of.elements.model.ChoicePurpose;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.EventType;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.choiceprocessor.BattlerActionChoiceProcessor;
import xigua.battle.of.elements.representation.cui.choiceprocessor.BattlerSummonElementChoiceProcessor;
import xigua.battle.of.elements.representation.cui.choiceprocessor.ChoiceProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.BattleActionEndedEventProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.BattleActionStartedEventProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.BattleElementSummonedEventProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.BattleMagicNotCastedEventProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.BattleStartedEventProcessor;
import xigua.battle.of.elements.representation.cui.eventprocessor.EventProcessor;

public class BattleCli implements BattlerController, BattlerObserver {
    private Battler battler;
    private final Map<EventType, Class<? extends EventProcessor>> eventTypeProcessorClassMap = new HashMap<>();
    private final Map<ChoicePurpose, Class<? extends ChoiceProcessor>> choicePurposeProcessorClassMap = new HashMap<>();

    public BattleCli() {
        eventTypeProcessorClassMap.put(EventType.BATTLE_STARTED, BattleStartedEventProcessor.class);
        eventTypeProcessorClassMap.put(EventType.BATTLE_ACTION_STARTED, BattleActionStartedEventProcessor.class);
        eventTypeProcessorClassMap.put(EventType.BATTLE_ACTION_ENDED, BattleActionEndedEventProcessor.class);
        eventTypeProcessorClassMap.put(EventType.BATTLE_ELEMENT_SUMMONED, BattleElementSummonedEventProcessor.class);
        eventTypeProcessorClassMap.put(EventType.BATTLE_MAGIC_NOT_CASTED, BattleMagicNotCastedEventProcessor.class);
        eventTypeProcessorClassMap.put(EventType.BATTLE_MAGIC_CASTED, BattleMagicCastedEventProcessor.class);

        choicePurposeProcessorClassMap.put(ChoicePurpose.BATTLE_ACTION, BattlerActionChoiceProcessor.class);
        choicePurposeProcessorClassMap.put(ChoicePurpose.BATTLE_SUMMON_ELEMENT, BattlerSummonElementChoiceProcessor.class);
    }

    @Override
    public void setBattler(Battler battler) {
        this.battler = battler;
    }

    @Override
    public Choices choose(Choices choices) {
        return dispatchChoices(choices);
    }

    @Override
    public void notify(Event event) {
        dispatchNotification(event);
    }

    private Choices dispatchChoices(Choices choices) {
        try {
            return choicePurposeProcessorClassMap.get(choices.getPurpose()).newInstance().process(battler, choices);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("An error occurs while processing event.", e);
        }
    }

    private void dispatchNotification(Event event) {
        try {
            eventTypeProcessorClassMap.get(event.getType()).newInstance().process(battler, event);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("An error occurs while processing event.", e);
        }
    }
}
