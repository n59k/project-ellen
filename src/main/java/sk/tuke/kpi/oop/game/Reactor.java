package sk.tuke.kpi.oop.game;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private final Set<EnergyConsumer> devices;
    private final Animation reactorExtinguishedAnimation;
    private final Animation hotreactorAnimation;
    private final Animation brokenreactorAnimation;
    private final Animation offReactor;
    private int damage = 0;


    private final Animation normalAnimation;
    private int temperature = 0;
    private boolean running;
    private Light LightsInReactor;


    public Reactor() {
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        temperature = 0;
        damage = 0;
        devices = new HashSet<>();
        reactorExtinguishedAnimation = new Animation("sprites/reactor_extinguished.png", 80, 80);
        hotreactorAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenreactorAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        offReactor = new Animation("sprites/reactor.png");
        turnOff();
    }


    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    private void updateAnimation() {
        if (!running) {
            if (damage >= 100) {
                setAnimation(brokenreactorAnimation);
            } else {
                setAnimation(offReactor);
            }
        } else if (temperature < 0) {
            return;
        } else if (temperature <= 4000) {
            setAnimation(normalAnimation);
        } else if (temperature < 6000) {
            setAnimation(hotreactorAnimation);
        } else {
            setAnimation(brokenreactorAnimation);
        }
        updateAllDevices();

    }

    public void increaseTemperature(int increment) {
        if (running && increment >= 0) {
            if (damage >= 33 && damage <= 66) {
                temperature = (int) (temperature + (1.5 * increment));
            } else if (damage > 66) {
                temperature = temperature + (2 * increment);
            } else {
                temperature = temperature + increment;
            }

            if (temperature > 2000) {
                int cur_temperature = temperature - 2000;
                //  this.damage = (int)((cur_temperature/(float)4000)*100);
                this.damage = Math.round((100 * cur_temperature) / 4000);
            }

            if (damage >= 100) {
                damage = 100;
                running = false;
            }
            updateAnimation();
        }
    }


    public void decreaseTemperature(int decrement) {
        if (running && decrement >= 0) {
            if (damage == 100) {
                updateAnimation();
            } else if (damage >= 50) {
                temperature = (int) (temperature - (decrement * 0.5));
                updateAnimation();
            } else {
                temperature = temperature - decrement;
                updateAnimation();
            }
            if (decrement < 0) {
                temperature = 0;
            }
        }

    }

    public boolean repair() {
        if (damage > 0 && damage < 100) {
            temperature = ((damage - 50) * 40) + 2000;
            if (damage > 50) {
                damage = damage - 50;
                updateAnimation();
            } else {
                damage = 0;
                updateAnimation();
            }
            return true;
        }
        return false;
    }

    public void turnOn() {
        if (temperature >= 0) {
            running = true;
            updateAnimation();
        }
        if (this.getDamage() >= 100) {
            running = false;
        } else {
            running = true;
            updateAnimation();
        }
        if (LightsInReactor != null) {
            LightsInReactor.setPowered(true);
        }

    }

    public void turnOff() {
        setAnimation(offReactor);
        running = false;
    }

    //setElectricityInDevices(false);


    public boolean isOn() {
        return running != false;
    }

    public void addDevice(EnergyConsumer device) {
        this.devices.add(device);
        if (running && damage == 0) {
            device.setPowered(true);
        }
        device.setPowered(running);
    }

    public boolean extinguish() {
        if (damage != 0 || running) {
            this.temperature = this.getTemperature() - 4000;
            setAnimation(reactorExtinguishedAnimation);
            return true;

        } else {
            return false;
        }
    }

    public void removeDevice(EnergyConsumer devise) {
        devise.setPowered(false);
        this.devices.remove(devise);
    }

    private boolean isItWorks() {
        return running && damage != 100;
    }

    private void updateStateOfDevice(EnergyConsumer device) {
        device.setPowered(isItWorks());
    }

    private void updateAllDevices() {
        this.devices.forEach(this::updateStateOfDevice);
    }


    ////    public void turnOn(){
////        running = true;
////        setAnimation(normalAnimation);
////    }
////    public void turnOff(){
////        running = false;
////        setAnimation(offReactor);
////        if(damage == 100){
////            running = false;
////            setAnimation(brokenreactorAnimation);
////        }
////        if(damage == 0 && temperature == 0){
////            running = false;
////            setAnimation(offReactor);
////        }
////
////    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);

    }

}

