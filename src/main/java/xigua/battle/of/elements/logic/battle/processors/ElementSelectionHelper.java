package xigua.battle.of.elements.logic.battle.processors;

import xigua.battle.of.elements.logic.battle.ElementFactory;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.ChoicesPurpose;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.FreeElementBank;

import java.util.ArrayList;
import java.util.List;

final class ElementSelectionHelper {
    private static final int NUM_OF_ELEMENTS_PER_ACTION = 2;
    private static final String GENERATED_ELEMENT = "GENERATED";
    private static final String STORED_ELEMENT = "STROED";

    protected static List<Element> generateElements(ElementFactory elementFactory) {
        List<Element> generatedElements = new ArrayList<>();
        for (int i = 0; i < NUM_OF_ELEMENTS_PER_ACTION; i++) {
            generatedElements.add(elementFactory.getElement());
        }

        return generatedElements;
    }

    protected static Choices buildElementSelectionChoices(ChoicesPurpose purpose, List<Element> generatedElements,
            FreeElementBank freeElementBank) {
        List<String> choiceStrings = new ArrayList<>();

        generatedElements.forEach(element -> choiceStrings.add(String.format("%s:%s:1", element.name(),
                GENERATED_ELEMENT)));
        freeElementBank.toDistinctList().forEach(element -> choiceStrings.add(String.format("%s:%s:%d", element.name
                (), STORED_ELEMENT, freeElementBank.countElement(element))));

        return new Choices(purpose, choiceStrings);
    }

    protected static Element getSelectedElement(Choices choices, List<Element> generatedElements, FreeElementBank
            freeElementBank) {
        Element selectedElement;

        int index = choices.getChosenIndex();

        if (index < generatedElements.size()) {
            selectedElement = generatedElements.remove(index);
        } else {
            selectedElement = freeElementBank.toDistinctList().get(index - generatedElements.size());
            freeElementBank.deleteElement(selectedElement);
        }

        return selectedElement;
    }
}
