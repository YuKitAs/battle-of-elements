package xigua.battle.of.elements.model.battle;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Element> toDistinctList() {
        return elementList.stream().distinct().collect(Collectors.toList());
    }
}
