package xigua.battle.of.elements.model.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummonedElementBank {
    private final List<Element> elementList;
    private final int maxSize;

    public SummonedElementBank(int maxSize) {
        elementList = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void add(Element element) {
        if (elementList.size() >= maxSize) {
            throw new RuntimeException("The summoned element bank is full.");
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
