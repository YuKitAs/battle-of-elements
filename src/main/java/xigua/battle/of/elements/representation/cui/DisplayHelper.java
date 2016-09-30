package xigua.battle.of.elements.representation.cui;

import java.util.stream.Collectors;

import xigua.battle.of.elements.model.battle.Element;
import xigua.battle.of.elements.model.battle.SummonedElementBank;

public final class DisplayHelper {
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

    public static String getSummonedElementBankDisplay(SummonedElementBank summonedElementBank) {
        return String.join(" ", summonedElementBank.toList().stream().map(DisplayHelper::getElementShortDisplay).collect(Collectors
                .toList()));
    }
}
