package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private List<ExhaustionEffect> efects;

    public Health(int Healthy) {
        MyHealth = Healthy;
        maxHealthy = MyHealth;
    }

    public Health(int Healthy, int maxHealthy) {
        MyHealth = Healthy;
        this.maxHealthy = maxHealthy;
        efects = new ArrayList<>();
    }
    public int getValue() {
        return MyHealth;
    }

    public void setValue(int he) {
        this.MyHealth = he;
    }

    public void restore() {
        this.MyHealth = this.maxHealthy;
    }




    private int MyHealth;
    private int maxHealthy;

    public void refill(int amount) {
        this.MyHealth = MyHealth + amount;
        if (this.MyHealth > this.maxHealthy) {
            restore();
        }
    }
    public void energyUp(){
            this.MyHealth = MyHealth + 5;
    }
    public void drain(int amount) {
        if (MyHealth <= amount) {
            if (MyHealth == 0) {
                if (efects != null) {
                    efects.forEach(ExhaustionEffect::apply);
                }
            } else {
                MyHealth = 0;
            }
        } else {
            MyHealth = MyHealth - amount;
        }
    }

    public void exhaust() {
        if (MyHealth == 0) {
            if (efects != null) {
                efects.forEach(ExhaustionEffect::apply);
            }
        } else if (MyHealth != 0) {
            MyHealth = 0;
            if (efects != null) {
                efects.forEach(ExhaustionEffect::apply);
            }
        }
    }


    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        if (efects != null) {
            efects.add(effect);
        }

    }


}
