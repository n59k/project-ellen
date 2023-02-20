package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private Disposable disposeLight;
    private boolean repaired;

    public DefectiveLight() {
        super();
        this.repaired = false;
    }

    public void changeLight() {
        repaired = false;
        int w = (int) (Math.random() * 20);
        if (w == 1) {
            super.toggle();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.disposeLight = new Loop<>(new Invoke<Actor>(this::changeLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if (disposeLight == null || repaired) {
            return false;
        } else {
            repaired = true;
            disposeLight.dispose();
        }

        this.disposeLight = new ActionSequence<>(new Wait<>(10), new Loop<>(new Invoke<>(this::toggle))).scheduleFor(this);
        return true;

    }

    public void breakLight() {
        this.disposeLight = new Loop<>(new Invoke<>(this::changeLight)).scheduleFor(this);
    }
}
