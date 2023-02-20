package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {
    private Usable<A> item;
    public Use(Usable<A> item) {
        this.item = item;
    }


    @Override
    public void execute(float deltaTime) {
        if (!isDone()) {
            item.useWith(getActor());
            setDone(true);
        }
    }

    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && item.getUsingActorClass().isInstance(actor)) {
                    return this.scheduleFor(item.getUsingActorClass().cast(actor));
            }
        }
        return null;
    }

}
