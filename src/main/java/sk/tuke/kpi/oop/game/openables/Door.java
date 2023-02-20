package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;


public class Door extends AbstractActor implements Openable, Usable<Ripley> {
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public enum  Orientation { VERTICAL, HORIZONTAL }
    private Animation openAnimation;
    private Animation closeAnimation;
    private boolean open;

    public Door() {
        closeAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
        openAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
        setAnimation(new Animation("sprites/vdoor.png", 16, 32));
        getAnimation().stop();
        open = false;
    }

    public Door (String name, Orientation orientation) {
        super(name);
        open = false;
        if (orientation == Orientation.VERTICAL) {
            openAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            closeAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(new Animation("sprites/vdoor.png", 16, 32));
            getAnimation().stop();
        }
        else if (orientation == Orientation.HORIZONTAL) {
            openAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            closeAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(new Animation("sprites/hdoor.png", 32, 16));
            getAnimation().stop();
        }

    }

    @Override
    public void useWith(Ripley actor) {
        if (isOpen()) {
            close();
        }else {
            open();
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }

    @Override
    public void open() {
        int posX = this.getPosX() /16;
        int posY = this.getPosY() / 16;
        int posYOpen = this.getPosY() / 16 + 1;
        open = true;
        Objects.requireNonNull(getScene()).getMap().getTile(posX, posY).setType(MapTile.Type.CLEAR);
        getScene().getMap().getTile(posX, posYOpen).setType(MapTile.Type.CLEAR);
        setAnimation(openAnimation);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_OPENED, this);

    }

    @Override
    public void close() {
        int posX = this.getPosX() /16;
        int posY = this.getPosY() / 16;
        int posYOpen = this.getPosY() / 16 + 1;
        open = false;
        Objects.requireNonNull(getScene()).getMap().getTile(posX, posY).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(posX, posYOpen).setType(MapTile.Type.WALL);
        setAnimation(closeAnimation);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16+1 ).setType(MapTile.Type.WALL);

    }
}
