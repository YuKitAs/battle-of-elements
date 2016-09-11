package xigua.battle.of.elements.model.magic;

import java.util.Collections;
import java.util.List;

public class Magic {
    public static final Magic EMPTY = new Magic(null, Collections.emptyList());

    private final Element element;
    private final List<Effect> effects;

    public Magic(Element element, List<Effect> effects) {
        this.element = element;
        this.effects = effects;
    }

    public Element getElement() {
        return element;
    }

    public List<Effect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    public enum Effect {
        DAMAGE, DEBUFF, BLOCK, BUFF, CLEANSE, HEAL;

        private int level;

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
}
