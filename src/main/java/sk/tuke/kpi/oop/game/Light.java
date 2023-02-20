package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    private boolean isOn;
    private boolean isPowered;
    private final Animation LightOn;
    private final Animation LightOff;


    public Light() {
        isOn = false;
        isPowered = false;
        LightOff = (new Animation("sprites/light_off.png"));
        LightOn = (new Animation("sprites/light_on.png"));
        setAnimation(LightOff);
    }

    public void toggle() {
        isOn = !isOn;
        updateAnimation();
    }


    private void updateAnimation() {
        if (this.isOn) {
            if (this.isPowered == false) {
                setAnimation(LightOff);

            } else {
                setAnimation(LightOn);
            }
        }
    }

    public void setElectricityFlow(boolean poweredInReactor) {
        isPowered = poweredInReactor;
    }

    public void turnOn() {
        this.isOn = true;
        if (this.isOn && this.isPowered) {
            setAnimation(LightOn);
        }
    }

    public boolean isOn() {
        return isOn;
    }


    public void turnOff() {
        this.isOn = false;
        setAnimation(LightOff);
    }

    public void setPowered(boolean powered) {
        this.isPowered = powered;
        updateAnimation();
    }


}
