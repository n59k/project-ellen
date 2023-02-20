package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;

import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;


public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    private Health health;
    private Behaviour<? super Alien> behaviour;
    private Disposable attack = null;

    public Alien() {
        Animation alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(alienAnimation);
        health = new Health(100, 100);
        health.onExhaustion(() -> deadAlien());
    }

    public Alien(int healthValue, Behaviour<? super Alien> behaviour) {
        Animation alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(alienAnimation);
        health = new Health(healthValue, 100);
        this.behaviour = behaviour;
        health.onExhaustion(() -> deadAlien());
    }

    public void deadAlien() {
        getScene().removeActor(this);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return 1;
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (behaviour != null) {
            behaviour.setUp(this);
        }
        attack = new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::startMovingAlien),
                new Wait<>(0.3f)
            )).scheduleFor(this);
    }

    public void startMovingAlien() {
        if (getScene() == null) {
            return;
        }

        for (Actor aliveActor : getScene().getActors()) {
            if (aliveActor instanceof Alive && !(aliveActor instanceof Enemy) && this.intersects(aliveActor)) {
                    ((Alive) aliveActor).getHealth().drain(12);
                    drainAlien();
            }
        }
    }
    public void drainAlien(){
        new ActionSequence<>(
            new Invoke<>(this::stopAlien),
            new Wait<>(1),
            new Invoke<>(this::startMovingAlien)
        ).scheduleFor(this);
    }

    public void stopAlien() {
        if (attack != null) {
            attack = null;
        } else {
            return;
        }
    }
}

