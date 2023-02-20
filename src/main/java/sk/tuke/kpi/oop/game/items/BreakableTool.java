package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.Objects;


public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {
    private int remaingU;


    public BreakableTool(int remainingUses) {
        this.remaingU = remainingUses;
    }

    @Override
    public void useWith(A actor) {
        this.remaingU--;
        if (this.remaingU == 0) {
            Objects.requireNonNull(getScene()).removeActor(this);
        }
    }

    public int getRemainingUses() {
        return remaingU;
    }
}
