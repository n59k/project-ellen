package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

import java.util.Objects;

public class Fire<A extends Armed> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime) {
        if (getActor() == null) {
            setDone(true);
            return;
        }
        if (isDone()) {
            return;
        }
        Fireable weapon = getActor().getFirearm().fire();
        int weaponX = getActor().getPosX() + 8 + Direction.fromAngle(getActor().getAnimation().getRotation()).getDx()*24;
        int weaponY = getActor().getPosY() + 8 + Direction.fromAngle(getActor().getAnimation().getRotation()).getDy()*24;
        if (Objects.nonNull(getActor()) && !isDone()) {
            if (Objects.isNull(weapon)) {
                setDone(true);
                return;
            }
            getActor().getFirearm().fire().getAnimation().setRotation(Direction.fromAngle(getActor().getAnimation().getRotation()).getAngle());
            getActor().getScene().addActor(weapon, weaponX, weaponY);
            new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(weapon);
        }
        setDone(true);
    }
}
