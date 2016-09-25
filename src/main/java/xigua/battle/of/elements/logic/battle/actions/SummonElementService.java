package xigua.battle.of.elements.logic.battle.actions;

import xigua.battle.of.elements.logic.battle.BattlerController;
import xigua.battle.of.elements.logic.battle.BattlerObserver;
import xigua.battle.of.elements.logic.battle.ElementFactory;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.ElementConsumeChoice;
import xigua.battle.of.elements.model.battle.actions.ElementUsage;
import xigua.battle.of.elements.model.battle.actions.Magic;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.utility.DeepCopy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SummonElementService {
    private static final int ELEMENTS_PER_ROUND = 2;
    private static final double ATTACK_LEVEL_FACTOR = 0.2;
    private static final double SHIELD_LEVEL_FACTOR = 0.2;

    private final BattleField battleField;
    private final Battler battler;
    private final BattlerController battlerController;
    private final BattlerObserver battlerObserver;
    private final ElementFactory elementFactory;

    public SummonElementService(BattleField battleField, Battler battler, BattlerController battlerController,
            BattlerObserver battlerObserver, ElementFactory elementFactory) {
        this.battleField = battleField;
        this.battler = battler;
        this.battlerObserver = battlerObserver;
        this.battlerController = battlerController;
        this.elementFactory = elementFactory;
    }

    public void processAction() {
        List<Element> generatedElements = getGeneratedElements(ELEMENTS_PER_ROUND);

        chooseElement();

        castMagic();
    }

    private void chooseElement() {
        List<Element> generatedElements = getGeneratedElements(ELEMENTS_PER_ROUND);

        ElementConsumeChoice choice = battlerController.chooseSummonedElement(getElementConsumeChoices(battler,
                generatedElements));

        switch (choice.getSource()) {
            case GENERATED:
                generatedElements.remove(choice.getElement());
                break;
            case FREE_ELEMENT_BANK:
                battler.getFreeElementBank().deleteElement(choice.getElement());
                break;
            default:
                throw new RuntimeException("Unknown source of the summoned element.");
        }

        battler.getSummonedElementBank().add(choice.getElement());

        if (battlerObserver != null) {
            battlerObserver.notifyAfterChoseElement(choice, DeepCopy.copy(battler), DeepCopy.copy(battleField));
        }
    }

    private void castMagic() {
        boolean isMagic = battler.getSummonedElementBank().getLast().getDestructedElement() == battler
                .getSummonedElementBank().getFirst();

        if (!isMagic) {
            return;
        }

        Magic magic = new MagicBuilder().fromSummonedElementBank(battler.getSummonedElementBank());

        if (battlerObserver != null) {
            battlerObserver.notifyAfterBuiltMagic(magic, DeepCopy.copy(battler), DeepCopy.copy(battleField));
        }

        if (magic == Magic.EMPTY) {
            return;
        }

        switch (magic.getUsage()) {
            case ATTACK:
                processAttackMagic(magic);
                break;
            case DEFEND:
                processDefendMagic(magic);
                break;
            case HEAL:
                processHealMagic(magic);
                break;
        }
    }

    private void processAttackMagic(Magic magic) {
        String targetName = battlerController.chooseTarget(battleField.getEnemiesFor(battler).stream().map
                (Battler::getName).collect(Collectors.toSet()));

        Battler target = battleField.getBattler(targetName);
        Battler targetBefore = DeepCopy.copy(target);

        double attack = battler.getAttack();
        Element type = magic.getType();
        int attackLevel = magic.getPrimaryLevel();
        int debuffLevel = magic.getSecondaryLevel();
        double defence = target.getDefence();
        int sheildLevel = target.getStates().stream()
                // Filter defend buffers:
                .filter(state -> state.getUsage() == ElementUsage.DEFEND && state.getType().getConstructedElement()
                        == type)
                // Get max level:
                .collect(Collectors.summingInt(Magic::getSecondaryLevel));

        int stateIndex = 0;
        do {
            Magic state = target.getStates().get(stateIndex);

            if (state.getUsage() != ElementUsage.DEFEND || state.getType().getConstructedElement() != type) {
                continue;
            }

            attackLevel -= state.getPrimaryLevel();
            if (attackLevel > 0) {
                state.setPrimaryLevel(0);
            } else {
                state.setPrimaryLevel(-attackLevel);
            }

            stateIndex++;
        } while (attackLevel > 0);

        if (attackLevel <= 0) {
            if (battlerObserver != null) {
                battlerObserver.notifyAppliedMagic(magic.getUsage(), targetBefore, targetBefore, battler, battleField);
            }

            return;
        }

        attack += ATTACK_LEVEL_FACTOR * attackLevel * attack;
        defence += SHIELD_LEVEL_FACTOR * sheildLevel * defence;

        int hitPointDelta = (int) (attack - defence);

        target.getHitPoint().setValue(target.getHitPoint().getValue() - hitPointDelta);
        Battler targetAfter = DeepCopy.copy(target);

        if (battlerObserver != null) {
            battlerObserver.notifyAppliedMagic(magic.getUsage(), targetBefore, targetAfter, battler, battleField);
        }
    }

    private void processDefendMagic(Magic magic) {
    }

    private void processHealMagic(Magic magic) {
    }

    private List<Element> getGeneratedElements(int number) {
        List<Element> generatedElements = new ArrayList<>();
        for (int i = 0; i < ELEMENTS_PER_ROUND; i++) {
            generatedElements.add(elementFactory.getElement());
        }

        return generatedElements;
    }

    private List<ElementConsumeChoice> getElementConsumeChoices(Battler battler, List<Element> generatedElements) {
        List<ElementConsumeChoice> choices = new ArrayList<>();

        generatedElements.stream().collect(Collectors.groupingBy(element -> element))
                // Map generated
                .entrySet().forEach(entry -> choices.add(new ElementConsumeChoice(entry.getKey(),
                ElementConsumeChoice.Source.GENERATED, entry.getValue().size())));

        battler.getFreeElementBank().toDistinctList().stream().collect(Collectors.groupingBy(element -> element))
                // Map generated
                .entrySet().forEach(entry -> choices.add(new ElementConsumeChoice(entry.getKey(),
                ElementConsumeChoice.Source.FREE_ELEMENT_BANK, entry.getValue().size())));

        return choices;
    }
}
