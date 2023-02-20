package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;


import java.util.Objects;

public class Case extends AbstractActor implements Usable<Ripley>, Collectible{


    public Case() {
        Animation caseAnimation = new Animation("sprites/case.png", 16, 16);
        setAnimation(caseAnimation);
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor != null) {
            int random = (int)(Math.random() * 3);
            if (random == 1) {
                actor.getHealth().setValue(1);
                actor.getFirearm().case1(2);
                (Objects.requireNonNull(getScene())).removeActor(this);
            }
            else if (random == 2){
                actor.getHealth().setValue(150);
                if (actor.getFirearm().getAmmo() != 500) {
                    actor.getFirearm().reload(500);
                }
                (Objects.requireNonNull(getScene())).removeActor(this);
            }
            else {
                actor.setGold(20);
                actor.getBackpack().setBackpackSize(5);
                (Objects.requireNonNull(getScene())).removeActor(this);
            }
        }else return;
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
