package xigua.battle.of.elements.model.battle.magic;

import java.util.LinkedList;
import java.util.List;

public abstract class ElementBank {
    protected final int maxSize;
    protected final List<Element> elementList;

    ElementBank(int maxSize) {
        this.maxSize = maxSize;
        this.elementList = new LinkedList<>();
    }

    public int getCurrentSize() {
        return elementList.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void add(Element element) {
        if (elementList.size() >= maxSize) {
            throw new RuntimeException("The element bank is full.");
        }

        elementList.add(element);
    }

    public Element remove(int index) {
        return elementList.remove(index);
    }

    public void clear() {
        elementList.clear();
    }
}
