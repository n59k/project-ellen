package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;

public class Energy extends AbstractActor implements Usable<Alive>, Collectible{
    public Energy() {
        Animation energyAnimation = new Animation("sprites/energy.png", 16, 16);
        setAnimation(energyAnimation);
    }


    @Override
    public void useWith(Alive actor) {
        if (actor != null) {
            if (actor.getHealth().getValue() != 150) {
                if (getScene() == null){
                    return;
                }else {
                    actor.getHealth().restore();
                    (Objects.requireNonNull(getScene())).removeActor(this);
                }
            }
        }else return;
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }


}
