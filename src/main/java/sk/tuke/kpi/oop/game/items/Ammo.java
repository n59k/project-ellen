package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Armed>, Collectible {
    public Ammo() {
        Animation ammoAnimation = new Animation("sprites/ammo.png", 16, 16);
        setAnimation(ammoAnimation);
    }

//    @Override
//    public void useWith(Ripley actor) {
//        if (actor != null) {
//            if (actor.getAmmo() != 500) {
//                if (actor.getAmmo() <= 450 && actor.getAmmo() != 500) {
//                    actor.setAmmo(actor.getAmmo() + 50);
//                    Objects.requireNonNull(getScene()).removeActor(this);
//                } else if (actor.getAmmo() >= 450){
//                    actor.setAmmo(500);
//                    Objects.requireNonNull(getScene()).removeActor(this);
//                }
//            }else if (actor.getAmmo() == 500){
//                return;
//            }
//        }else if (actor == null){
//            return;
//        }
//    }

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
