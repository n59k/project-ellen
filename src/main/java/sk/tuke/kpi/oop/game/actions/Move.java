package sk.tuke.kpi.oop.game.actions;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
public class Move<A extends Movable> implements Action<A> {

    private final Direction direction;
    private A actor;
    private float duration;
    private int callFirst;
    private boolean SetIsDone;
    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        //implementacia konstruktora akcie
        SetIsDone = false;
        callFirst = 0;
    }
    public Move(Direction direction) {
        callFirst = 0;
        this.direction = direction;
    }
    private void setPosition0(){
        callFirst = 0;
        duration = 0;
    }

    @Override
    public A getActor() {
        return actor;
    }

    @Override
    public void setActor(A direct) {
        actor = direct;
    }

    @Override
    public boolean isDone() {
        return SetIsDone;
    }

    @Override
    public void reset() {
        actor.stoppedMoving();
        setPosition0();
        SetIsDone = false;

    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() != null) {

            duration = duration - deltaTime;
            if (!isDone()) {
                if (callFirst == 0) {
                    actor.startedMoving(direction);
                    callFirst += 1;
                }
                if (duration > 0) {
                    float z1 = actor.getPosX() + direction.getDx() * actor.getSpeed();
                    float z2 = actor.getPosY() + direction.getDy() * actor.getSpeed();
                    durationSet0(z1, z2);
                }
                else{
                    //od toho momentu musí metóda isDone() vrátiť hodnotu true
                    if (actor != null) {
                        SetIsDone = true;
                        actor.stoppedMoving();
                    }
                }
            }
        }else {
            return;
        }
    }
    //
    private void durationSet0(float z1, float z2){
        actor.setPosition((int) z1,(int) z2);
        if ((getActor().getScene()).getMap().intersectsWithWall(actor)) {
            actor.setPosition(actor.getPosX() - direction.getDx() * actor.getSpeed(), actor.getPosY() - direction.getDy() * actor.getSpeed());
            actor.collidedWithWall();
        }
    }
    //дополнительная функция стоп
    //od toho momentu musí metóda isDone() vrátiť hodnotu true
    public void stop() {
        if (actor != null) {
            SetIsDone = true;
            actor.stoppedMoving();
        }
    }
//arena test
}

