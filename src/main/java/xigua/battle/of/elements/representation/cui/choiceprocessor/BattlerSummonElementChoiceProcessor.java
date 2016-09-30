package xigua.battle.of.elements.representation.cui.choiceprocessor;

import xigua.battle.of.elements.logic.ChoicesBuilder;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.representation.cui.DisplayHelper;

public class BattlerSummonElementChoiceProcessor extends AbstractChoiceProcessor {
    @Override
    protected String getChoicesDisplayString(String choiceString) {
        String[] choiceParts = choiceString.split(":");

        if (choiceParts[0].equals("NONE")) {
            return "不召唤任何元素";
        }

        String elementDisplay = DisplayHelper.getElementDisplay(Element.valueOf(choiceParts[0]));
        String sourceDisplay = DisplayHelper.getSourceDisplayString(ChoicesBuilder.ElementSource.valueOf
                (choiceParts[1]));
        String quantityDisplay = choiceParts[2];

        return String.format("%s[%s][%sx]", elementDisplay, sourceDisplay, quantityDisplay);
    }

    @Override
    protected String getChoicesListHeader() {
        return "召唤哪个元素？";
    }
}
