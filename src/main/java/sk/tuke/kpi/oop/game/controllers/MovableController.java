package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {

    private final Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.W, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.D, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.S, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.A, Direction.WEST)
    );

    //мои доп функции
    private final Set<Input.Key> keyboard;
    private final Movable actor;
    public MovableController(Movable actor) {
        this.actor = actor;
        keyboard = new HashSet<>();
    }
    private Input.Key onekey =null;
    private Input.Key doublekey =null;
    private Move<Movable> move = null;
    private Disposable disposable;

    @Override
    public void keyPressed(Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            if(onekey == null)
            {
                onekey = key;
            }
            else if(doublekey == null)
            {
                doublekey = key;
            }
            keyboard.add(key);
            updateActorMove();
        }

    }

    @Override
    public void keyReleased(Input.Key key) {
        if(keyDirectionMap.containsKey(key))
        {
            if (key == onekey) {
                onekey = null;
            }

            if (key == doublekey) {
                doublekey = null;
            }
            keyboard.remove(key);
            updateActorMove();
        }
    }
    private void updateActorMove() {
        Direction directionUpdate = null;
        int counter = 0;
        for (Input.Key input_key:keyboard) {
            if (counter==0)
                directionUpdate=keyDirectionMap.get(input_key);
            else
                directionUpdate = directionUpdate.combine(keyDirectionMap.get(input_key));
            counter++;
        }
        stopActorMove();
        if (directionUpdate != null) {
            move = new Move<>(directionUpdate, Float.MAX_VALUE);
            disposable = move.scheduleFor(actor);
        }
    }
    //функция для стопа
    private void stopActorMove() {
        if (move != null) {
            move.stop();
            disposable.dispose();
            move = null;
        }
    }
}
