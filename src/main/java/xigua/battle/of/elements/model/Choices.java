package xigua.battle.of.elements.model;

import java.util.Collections;
import java.util.List;

public class Choices {
    private int chosenIndex;
    private final ChoicesPurpose purpose;
    private final List<String> choices;

    public Choices(ChoicesPurpose purpose, List<String> choices) {
        this.purpose = purpose;
        this.choices = choices;
    }

    public ChoicesPurpose getPurpose() {
        return purpose;
    }

    public List<String> getChoices() {
        return Collections.unmodifiableList(choices);
    }

    public void choose(int chosenIndex) {
        this.chosenIndex = chosenIndex;
    }

    public int getChosenIndex() {
        return chosenIndex;
    }

    public String getChosenItem() {
        return choices.get(getChosenIndex());
    }
}
