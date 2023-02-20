package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift <A extends Keeper> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime) {
        if (getActor() != null){
            if (!isDone()) {
                getActor().getBackpack().shift();
            }else {
                setDone(true);
                return;
            }
        }else {
            setDone(true);
            return;
        }
        setDone(true);
    }
}
