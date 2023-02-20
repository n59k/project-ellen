package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class AmmoLazer extends AbstractActor implements Usable<Armed>, Collectible {
    public AmmoLazer() {
        Animation ammoAnimation = new Animation("sprites/ammo2.png", 16, 16);
        setAnimation(ammoAnimation);
    }

    @Override
    public void useWith(Armed actor) {
        if (actor != null) {
            if (actor.getFirearm().getAmmo() != 500) {
                actor.getFirearm().reload(50);
                Objects.requireNonNull(getScene()).removeActor(this);
            }
        } else return;
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
