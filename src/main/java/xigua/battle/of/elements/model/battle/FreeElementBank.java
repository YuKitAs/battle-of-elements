package xigua.battle.of.elements.model.battle;

public class FreeElementBank extends ElementBank {
    public FreeElementBank(int maxSize) {
        super(maxSize);
    }

    public boolean hasElement(Element element) {
        return elementList.contains(element);
    }

    public Element getOneAndDelete(Element element) {
        if (hasElement(element)) {
            return null;
        }

        return element;
    }
}
