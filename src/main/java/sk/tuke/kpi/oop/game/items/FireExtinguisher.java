package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }

    public FireExtinguisher() {
        super(1);
        Animation extinguisherAnimation = new Animation("sprites/extinguisher.png");
        setAnimation(extinguisherAnimation);
    }

    public void useWith(Reactor reactor) {
        if (reactor == null) {
            return;
        } else if (reactor.extinguish()) {
            super.useWith(reactor);

        }
    }


}
