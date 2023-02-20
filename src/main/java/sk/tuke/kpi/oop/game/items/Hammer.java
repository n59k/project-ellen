package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class Hammer extends BreakableTool<Reactor> implements Collectible{
    public Hammer() {

        super(1);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    public Hammer(int remainingU) {
        super(remainingU);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    @Override
    public void useWith(Reactor reactor) {
        if (reactor == null) {
            return;
        } else if (reactor.repair()) {
            super.useWith(reactor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }


}



