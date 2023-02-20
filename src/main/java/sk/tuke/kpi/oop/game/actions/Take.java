package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;

public class Take<A extends Keeper> extends AbstractAction<A> {

    public Take() {

    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null) {
            setDone(true);
            return;
        }
        if (getActor().getScene() == null) {
            setDone(true);
            return;
        }
        if (!isDone()) {
            List<Actor> takeAction = Objects.requireNonNull(getActor().getScene()).getActors();

            for (Actor items : takeAction) {
                if (items instanceof Collectible && items.intersects(getActor())) {
                    try {
                        // kod ktory moze sposobit vynimku
                        getActor().getBackpack().add(((Collectible) items));
                        getActor().getScene().removeActor(items);
                        break;
                    } catch (IllegalStateException exception) {
                        // spracovanie vynimky typu Exception
                        // spravu vynimky ziskate metodou ex.getMessage()
                        getActor().getScene().getOverlay().drawText(exception.getMessage(), 0, 0).showFor(2);
                    }
                    setDone(true);
                }

            }
            setDone(true);
        }
    }

}
