package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;


public class KeeperController implements KeyboardListener {
    private Keeper keeppercontroller;

    public KeeperController(Keeper keeper) {
        this.keeppercontroller = keeper;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        switch (key) {
            case ENTER:
                new Take<>().scheduleFor(keeppercontroller);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(keeppercontroller);
                break;
            case Q:
                new Shift<>().scheduleFor(keeppercontroller);
                break;
            case U:
                this.useForTop();
                break;
            case B:
                this.useFirstTake();
                break;
            case G:
                this.useForTop();
                break;
            default:
                break;
        }
    }

    private void useForTop() {
        for (Actor item : this.keeppercontroller.getScene().getActors()) {
            if (item instanceof Usable && this.keeppercontroller.intersects(item)) {
                new Use<>((Usable<?>) item).scheduleForIntersectingWith(this.keeppercontroller);
            }
        }
    }

    private void useFirstTake() {
        Collectible itemOfBackpack = this.keeppercontroller.getBackpack().peek();
        if (itemOfBackpack instanceof Usable) {
            new Use<>((Usable<?>) itemOfBackpack).scheduleForIntersectingWith(this.keeppercontroller);
            this.keeppercontroller.getBackpack().remove(Objects.requireNonNull(itemOfBackpack));
        }

    }
}
