package xigua.battle.of.elements.model.battle;

public class SummonedElementBank extends ElementBank {
    public SummonedElementBank(int maxSize) {
        super(maxSize);
    }

    public Element getFirst() {
        return elementList.get(0);
    }

    public Element getSecond() {
        return elementList.get(1);
    }

    public Element getLast() {
        return elementList.get(elementList.size() - 1);
    }

    public int getElementLevelCount(Element element) {
        return (int) elementList.subList(2, elementList.size()).stream().filter(elementInList -> elementInList ==
                element).count();
    }
}
