package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private boolean powered;


    public Computer() {
        Animation normalAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
    }

    private void updateAnimation() {
        if (!powered) {
            this.getAnimation().pause();

        }
        this.getAnimation().play();
    }


    public void setPowered(boolean powered) {
        this.powered = powered;
        this.updateAnimation();

    }

    public int sub(int x, int z) {
        if (powered) {
            return x - z;
        }
        return 0;

    }

    public float sub(float x, float z) {
        if (powered) {
            return x - z;
        }
        return 0;

    }

    public int add(int x, int z) {
        if (powered) {
            return x + z;
        }
        return 0;

    }

    public float add(float x, float z) {

        if (powered) {
            return x + z;
        }
        return 0;
    }


}
