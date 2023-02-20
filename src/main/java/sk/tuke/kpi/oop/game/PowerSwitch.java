package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private final Switchable switchable;
    //private Reactor reactor;


    public PowerSwitch(Switchable switchable) {
        //this.reactor = reactor;
        this.switchable = switchable;
        Animation normalController;
        normalController = new Animation("sprites/switch.png", 16, 16);
        setAnimation(normalController);
    }

    //    public void toggle() {
//        if (reactor.isOn()) {
//            reactor.turnOff();
//        }
//        else {
//            reactor.turnOn();
//        }
//    }
    public Switchable getDevice() {
        return this.switchable;
    }

    public void switchOn() {
        if (switchable == null) {
            return;
        } else {
            this.switchable.turnOn();
            getAnimation().setTint(Color.WHITE);
        }
    }


    public void switchOff() {
        if (switchable != null) {
            this.switchable.turnOff();
            getAnimation().setTint(Color.GRAY);

        } else {
            return;
        }
    }


}
