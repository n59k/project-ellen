package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<A extends Keeper> extends AbstractAction<A> {
    public Drop() {
    }

    @Override
    public void execute(float deltaTime) {
        try {
            Collectible dropAction = getActor().getBackpack().peek();
            getActor().getScene().addActor(
                dropAction,
                (getActor().getPosX() + (getActor().getWidth() - dropAction.getWidth() / 2)),
                (getActor().getPosY() + (getActor().getHeight() - dropAction.getHeight() / 2))
            );
            getActor().getBackpack().remove(dropAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDone(true);

//        if (getActor().getBackpack().peek() == null) {
//            setDone(true);
//            return;
//        }
//        if (getActor().getScene() == null) {
//            setDone(true);
//            return;
//        }
//        if (getActor() == null) {
//            setDone(true);
//            return;
//        }
//        if (!isDone()) {
//            Collectible dropAction = getActor().getBackpack().peek();
//            getActor().getScene().addActor(dropAction,
//                (getActor().getPosX() + (getActor().getWidth() - dropAction.getWidth() / 2)),
//                (getActor().getPosY() + (getActor().getHeight() - dropAction.getHeight() / 2)));
//            getActor().getBackpack().remove(dropAction);
//        }
//        setDone(true);
    }
    public void remove(Collectible actionDrop){
        getActor().getBackpack().remove(actionDrop);
    }
}
