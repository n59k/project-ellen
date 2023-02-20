package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Gold extends AbstractActor implements Usable<Ripley>, Collectible{
    public Gold() {
        Animation goldAnimation = new Animation("sprites/gold2.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(goldAnimation);
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor.getGold() != 100) {
                actor.setGold(10);
                actor.getHealth().energyUp();
                Objects.requireNonNull(getScene()).removeActor(this);
        } else return;
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
