package xigua.battle.of.elements.model;

import java.util.Collections;
import java.util.List;

public class Choices {
    private int chosenIndex;
    private final ChoicePurpose purpose;
    private final List<String> choices;

    public Choices(ChoicePurpose purpose, List<String> choices) {
        this.purpose = purpose;
        this.choices = choices;
    }

    public ChoicePurpose getPurpose() {
        return purpose;
    }

    public List<String> getChoices() {
        return Collections.unmodifiableList(choices);
    }

    public void choose(int chosenIndex) {
        if (chosenIndex >= choices.size() || chosenIndex < 0) {
            throw new IndexOutOfBoundsException("Chosen index must be within the range of 0 to " + choices.size());
        }

        this.chosenIndex = chosenIndex;
    }

    public int getChosenIndex() {
        return chosenIndex;
    }

    public String getChosenItem() {
        return choices.get(getChosenIndex());
    }
}
