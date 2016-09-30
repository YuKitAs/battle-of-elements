package xigua.battle.of.elements.logic.battle.ai;

import xigua.battle.of.elements.logic.battle.BattlerController;
import xigua.battle.of.elements.logic.battle.BattlerObserver;
import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.Event;
import xigua.battle.of.elements.model.battle.battler.Battler;

public class VeryStupidAI implements BattlerController, BattlerObserver {
    @Override
    public void setBattler(Battler battler) {

    }

    @Override
    public Choices choose(Choices choices) {
        choices.choose(0);

        return choices;
    }

    @Override
    public void notify(Event event) {

    }
}
