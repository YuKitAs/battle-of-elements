package xigua.battle.of.elements.representation.cui.choiceprocessor;

import xigua.battle.of.elements.logic.battle.actionprocessor.ElementSelectionHelper;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.representation.cui.DisplayHelper;

public class BattlerSummonElementChoiceProcessor extends AbstractChoiceProcessor {
    @Override
    protected String getChoicesDisplayString(String choiceString) {
        String[] choiceParts = choiceString.split(":");

        String elementDisplay = DisplayHelper.getElementDisplay(Element.valueOf(choiceParts[0]));
        String sourceDisplay = getSourceDisplayString(choiceParts[1]);
        String quantityDisplay = choiceParts[2];

        return String.format("%s[%s][%sx]", elementDisplay, sourceDisplay, quantityDisplay);
    }

    @Override
    protected String getChoicesListHeader() {
        return "召唤哪个元素？";
    }

    private String getSourceDisplayString(String sourceString) {
        switch (sourceString) {
            case ElementSelectionHelper.GENERATED_ELEMENT:
                return "生成";
            case ElementSelectionHelper.STORED_ELEMENT:
                return "元素瓶";
            default:
                throw new RuntimeException("Wrong element source.");
        }
    }
}
