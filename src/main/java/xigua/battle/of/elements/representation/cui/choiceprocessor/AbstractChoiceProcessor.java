package xigua.battle.of.elements.representation.cui.choiceprocessor;

import xigua.battle.of.elements.model.Choices;
import xigua.battle.of.elements.model.battle.battler.Battler;
import xigua.battle.of.elements.representation.cui.InteractionHelper;

public abstract class AbstractChoiceProcessor implements ChoiceProcessor {
    @Override
    public Choices process(Battler battler, Choices choices) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < choices.getChoices().size(); i++) {
            sb.append(String.format("  %d. %s\n", i + 1, getChoicesDisplayString(choices.getChoices().get(i))));
        }

        InteractionHelper.printEmptyAndLine(getChoicesListHeader());
        InteractionHelper.printLine(sb.toString());

        int choice = InteractionHelper.readInteger("请选择：", 1, choices.getChoices().size(), "选择的范围不对！");

        choices.choose(choice - 1);

        return choices;
    }

    protected abstract String getChoicesDisplayString(String choiceString);

    protected abstract String getChoicesListHeader();
}
