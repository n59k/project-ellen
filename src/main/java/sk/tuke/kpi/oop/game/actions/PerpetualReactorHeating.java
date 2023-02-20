package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;




public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private final int inc;


    public PerpetualReactorHeating(int increment) {
        this.inc = increment;
    }

    @Override
    public void execute(float deltaTime) {
        Reactor reactor = getActor();
        if (reactor != null) {
            reactor.increaseTemperature(inc);
        }

    }

}
