package xigua.battle.of.elements.model.battle.magic;

public class FreeElementBank extends ElementBank {
    public FreeElementBank(int maxSize) {
        super(maxSize);
    }

    public boolean hasElement(Element element) {
        return elementList.contains(element);
    }

    public void deleteElement(Element element) {
        if (!elementList.remove(element)) {
            throw new RuntimeException("No such element in element bank.");
        }
    }
}
