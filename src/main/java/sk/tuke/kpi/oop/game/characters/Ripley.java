package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;


public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("RIPLEY DIED", Ripley.class);
    private final Animation playerAnimation;
    private final Animation deadAnimation;
    private int speed;
    private int ammo;
    private int gold;
    private int drink;
    private int energy = 90;
    private Health health;
    private Backpack backpack;
    private Firearm gun;


    public Ripley() {
        super("Ellen");

        playerAnimation = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(playerAnimation);
        deadAnimation = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
        speed = 2;
        //ammo = 500;
        gold = 0;
        drink = 0;
        health = new Health(90, 150);
        backpack = new Backpack("Ripley's backpack", 10);
        playerAnimation.stop();
        gun = new Gun(150,300);

        health.onExhaustion(() -> {
            this.setAnimation(deadAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
        });


    }
    public int getEnergy(){return energy;}

    public int getGold(){return gold;}
    public void setGold(int gold){
        this.gold += gold;
    }

    public int getDrink(){return drink;}
    public void setDrink(int drink){
        this.drink += drink;
    }

    public void setEnergy(int energy){
        if(this.energy != energy){
            getScene().getGame().getOverlay().drawText(
                "  Energy set to " + energy,
                getScene().getGame().getWindowSetup().getHeight() / 2, getScene().getGame().getWindowSetup().getWidth() / 2
            ).showFor(3);
        }
        this.energy = energy;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void callDiedWithNoHealth() {
        if (health.getValue() <= 0) {
            this.setAnimation(deadAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
         this.speed = speed;
    }


    @Override
    public void startedMoving(Direction direction) {
        //Movable.super.startedMoving();
        playerAnimation.setRotation(direction.getAngle());
        playerAnimation.play();
    }

    @Override
    public void stoppedMoving() {
        //Movable.super.stoppedMoving();
        playerAnimation.stop();
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return this.gun;
    }

    @Override
    public void setFirearm(Firearm firearm) {
        this.gun = firearm;
    }

}
