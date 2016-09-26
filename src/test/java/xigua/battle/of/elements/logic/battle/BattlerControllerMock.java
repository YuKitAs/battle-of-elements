package xigua.battle.of.elements.logic.battle;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BattlerControllerMock implements BattlerController {
    private final List<Choices> choicesReceived = new LinkedList<>();
    private final Queue<Integer> choicesToProvide = new LinkedList<>();

    @Override
    public void setBattler(Battler battler) {
        // Simply do nothing here.
    }

    @Override
    public Choices choose(Choices choices) {
        choicesReceived.add(choices);
        choices.choose(choicesToProvide.poll());
        return choices;
    }

    public void provideChoice(int choice) {
        choicesToProvide.add(choice);
    }

    public List<Choices> getChoicesReceived() {
        return choicesReceived;
    }
}