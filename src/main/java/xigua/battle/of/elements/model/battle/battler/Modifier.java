package xigua.battle.of.elements.model.battle.battler;

import java.io.Serializable;

public class Modifier implements Serializable {
    private static final long serialVersionUID = 1L;

    private double modifierFactor = 1;
    private int restActions = 0;

    public void updateRestActions() {
        if (restActions == 0) {
            modifierFactor = 1;
            return;
        }

        restActions--;
    }

    public void setModifier(double modifierFactor, int restActions) {
        this.modifierFactor = modifierFactor;
        this.restActions = restActions;
    }

    protected double getModifierFactor() {
        return modifierFactor;
    }
}
