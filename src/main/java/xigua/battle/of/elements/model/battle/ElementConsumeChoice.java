package xigua.battle.of.elements.model.battle;

import java.io.Serializable;

public class ElementConsumeChoice implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Element element;
    private final Source source;
    private final int number;

    public ElementConsumeChoice(Element element, Source source, int number) {
        this.element = element;
        this.source = source;
        this.number = number;
    }

    public Element getElement() {
        return element;
    }

    public Source getSource() {
        return source;
    }

    public int getNumber() {
        return number;
    }

    public enum Source {
        GENERATED, FREE_ELEMENT_BANK
    }
}
