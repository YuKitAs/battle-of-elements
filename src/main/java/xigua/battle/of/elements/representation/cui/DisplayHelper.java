package xigua.battle.of.elements.representation.cui;

import xigua.battle.of.elements.logic.ChoicesBuilder;
import xigua.battle.of.elements.model.battle.BattleField;
import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.SummonedElementBank;
import xigua.battle.of.elements.model.battle.battler.Battler;

import java.util.List;
import java.util.stream.Collectors;

public final class DisplayHelper {
    public static String getBattleFieldDisplay(BattleField battleField) {
        List<String> battlerDisplays = battleField.getAllBattlers().stream()
                // Display friendly battlers first:
                .sorted((b1, b2) -> (b1.isFriendly() ? -1 : 1) - (b2.isFriendly() ? -1 : 1))
                // Get battler displays:
                .map(battler -> getBattlerNameDisplay(battler) + getBattlerFactionDisplay(battler)).collect
                        (Collectors.toList());

        return String.join(" ", battlerDisplays);
    }

    public static String getBattlerFactionDisplay(Battler battler) {
        return battler.isFriendly() ? "[友]" : "[敌]";
    }

    public static String getBattlerNameDisplay(Battler battler) {
        return battler.getName();
    }

    public static String getElementDisplay(Element element) {
        switch (element) {
            case FIRE:
                return "火之元素";
            case WATER:
                return "水之元素";
            case WOOD:
                return "木之元素";
            default:
                return "虚无";
        }
    }

    public static String getElementShortDisplay(Element element) {
        switch (element) {
            case FIRE:
                return "火";
            case WATER:
                return "水";
            case WOOD:
                return "木";
            default:
                return "无";
        }
    }

    public static String getSourceDisplayString(ChoicesBuilder.ElementSource elementSource) {
        switch (elementSource) {
            case STORED:
                return "元素瓶";
            case SUMMONED:
                return "已召唤";
            case GENERATED:
            default:
                return "生成";
        }
    }

    public static String getSummonedElementBankDisplay(SummonedElementBank summonedElementBank) {
        StringBuilder sb = new StringBuilder();

        if (summonedElementBank.getCurrentSize() == 0) {
            sb.append("<空>");
        }

        sb.append(String.join(" ", summonedElementBank.toList().stream().map(DisplayHelper::getElementShortDisplay)
                .collect(Collectors.toList())));

        String summonedElementsDisplay = sb.toString();

        String elementBankSizeDisplay = String.format("(%d/%d)", summonedElementBank.getCurrentSize(),
                summonedElementBank.getMaxSize());

        return summonedElementsDisplay + " " + elementBankSizeDisplay;
    }
}
