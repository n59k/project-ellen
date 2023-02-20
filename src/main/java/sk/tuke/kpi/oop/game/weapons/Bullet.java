package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Drink;


public class Bullet extends AbstractActor implements Fireable, Collectible {
    private int speed;
    private int damage = 30;
    public Bullet() {
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
        speed = 6;

    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        if (direction != null && direction != Direction.NONE) {
            this.getAnimation().setRotation(direction.getAngle());
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<Actor>(() -> {
            scene.getActors().forEach(
                actor -> {
                    if (!(actor instanceof Ripley) && (actor instanceof Alive && this.intersects(actor))) {

                        ((Alive) actor).getHealth().drain(10);
                        scene.removeActor(this);
                    }
                }
            );
        })).scheduleFor(this);

}

    @Override
    public void collidedWithWall() {
        getScene().removeActor(this);
    }

}

