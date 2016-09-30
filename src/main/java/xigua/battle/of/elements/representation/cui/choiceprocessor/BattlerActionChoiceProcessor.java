package xigua.battle.of.elements.representation.cui.choiceprocessor;

public class BattlerActionChoiceProcessor extends AbstractChoiceProcessor {
    @Override
    protected String getChoicesDisplayString(String choiceString) {
        switch (choiceString) {
            case "PHYSICAL_ATTACK":
                return "攻击";
            case "SUMMON_ELEMENT":
                return "召唤元素";
            default:
                return "逃跑";
        }
    }

    @Override
    protected String getChoicesListHeader() {
        return "要做什么？";
    }
}
