package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private boolean on;
    private final Reactor reactor;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        setAnimation(new Animation("sprites/fan.png", 32, 32, 0.2f));
        turnOff();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }


    public Reactor getReactor() {
        return reactor;
    }

    private void coolReactor() {
        if (on && reactor != null) {
            reactor.decreaseTemperature(1);
        } else {
            return;
        }

    }

    public void turnOn() {
        on = true;
        getAnimation().play();
    }

    public void turnOff() {
        on = false;
        getAnimation().stop();
    }

    public boolean isOn() {
        return on;
    }


}
