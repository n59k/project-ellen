package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Drink extends AbstractActor implements Usable<Ripley>, Collectible{
    public Drink(){
        Animation drinkAnimation = new Animation("sprites/energy-drink.png", 16, 16);
        setAnimation(drinkAnimation);
    }
    @Override
    public void useWith(Ripley actor) {
            if (actor.getDrink() == 2) {
                    if (getScene() == null) {
                    } else {
                        actor.getHealth().setValue(20);
                        actor.setSpeed(1);
                        (Objects.requireNonNull(getScene())).removeActor(this);

                    }
            }else{
                actor.setDrink(1);
                actor.getHealth().energyUp();
                actor.setSpeed(4);
                (Objects.requireNonNull(getScene())).removeActor(this);
            }
    }


    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
