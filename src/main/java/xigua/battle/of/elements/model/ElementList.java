package xigua.battle.of.elements.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElementList {
    private final List<Element> elementList;
    private final int maxSize;

    public ElementList(int maxSize) {
        elementList = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void add(Element element) {
        if (elementList.size() >= maxSize) {
            throw new ElementListFullException();
        }

        elementList.add(element);
    }

    public Element getLast() {
        return elementList.get(elementList.size() - 1);
    }

    public List<Element> toList() {
        return Collections.unmodifiableList(elementList);
    }

    public void clear() {
        elementList.clear();
    }
}
