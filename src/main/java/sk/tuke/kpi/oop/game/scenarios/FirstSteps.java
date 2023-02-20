package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.DoorCoins;
import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.weapons.Gun2;

public class FirstSteps implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        for (Actor actor : scene.getActors()) {
            if (actor instanceof Ripley) {
                scene.follow(actor);
                setUpListeners(scene, (Ripley) actor);
                setUpBackPack(scene.getGame(), (Ripley) actor);
            }
        }
    }

    private void setUpListeners(Scene scene, Ripley ripley) {
        Disposable mc = scene.getInput().registerListener(new MovableController(ripley));
        Disposable kc = scene.getInput().registerListener(new KeeperController(ripley));
        Disposable sc = scene.getInput().registerListener(new ShooterController(ripley));

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> mc.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> kc.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> sc.dispose());

    }

    private void setUpBackPack(Game game, Ripley ripley) {
        ripley.getBackpack().add(new Hammer());
        ripley.getBackpack().add(new FireExtinguisher());
        ripley.getBackpack().add(new Wrench());
        ripley.getBackpack().add(new Wrench());
        game.pushActorContainer(ripley.getBackpack());
    }

    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            switch (name) {
                case "ripley":
                    return new Ripley();
                case "weapon":
                    return new Ammo();
                case "alien":
                    return new Alien();
                case "alienrndm":
                    return new Alien(150, new RandomlyMoving());
                case "kit":
                    return new Energy();
                case "gold":
                    return new Gold();
                case "drink":
                    return new Drink();
                case "door":
                    Door.Orientation Orientation = type.equals("vertical")?Door.Orientation.VERTICAL: Door.Orientation.HORIZONTAL;
                    return new Door("door", Orientation);
                case "doorCoin":
                    return new DoorCoins();
                case "lockedDoor":
                    return new LockedDoor();
                case "card":
                    return new AccessCard();
                case "case":
                    return new Case();
                case "lazer":
                    return new Gun2();
                default:
                    return null;
            }
        }
    }
    @Override
    public void sceneUpdating(@NotNull Scene scene){
        announcement(scene);
        playerInformation(scene);
        lastNotification(scene);

//        int locationsForInstruction = (windowHeight - GameApplication.STATUS_LINE_OFFSET) - 20;
//
//        if (ripley.getHealth().getValue() >= 1) {
//
//            scene.getGame().getOverlay().drawText(
//                "Q-change items in Backpack",
//                10, locationsForInstruction - 25
//            ).showFor(1);
//            scene.getGame().getOverlay().drawText(
//                "U, G - take item",
//                10, locationsForInstruction - 45
//            ).showFor(1);
//            scene.getGame().getOverlay().drawText(
//                "B - use top item from Backpack",
//                10, locationsForInstruction - 65
//            ).showFor(5);
//
//            scene.getGame().getOverlay().drawText(
//                "BACKSPACE - drop item",
//                10, locationsForInstruction - 85
//            ).showFor(5);
//            scene.getGame().getOverlay().drawText(
//                "ENTER - take item",
//                10, locationsForInstruction - 105
//            ).showFor(5);
//            scene.getGame().getOverlay().drawText(
//                "SPACE - fire",
//                10, locationsForInstruction - 125
//            ).showFor(5);
//        }

    }

    void announcement(Scene scene){
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley.getDrink() == 2){
            scene.getGame().getOverlay().drawText(
                "He drank too much! Take a rest!",
                303, 450
            ).showFor(2);
        }

        if (ripley.getGold() == 8){
            scene.getGame().getOverlay().drawText(
                "You're almost done!",
                303, 450
            ).showFor(3);

        }

    }
    void playerInformation(Scene scene){
        Animation energyAnimation = new Animation("sprites/energy.png", 16, 16);
        Animation ammoAnimation = new Animation("sprites/ammo.png", 16, 16);
        Animation goldAnimationStatic = new Animation("sprites/gold2.png", 16, 16);
        Animation backpackAnimation = new Animation("sprites/backpack.png", 16, 16);
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        int backpack = (ripley.getBackpack().getCapacity());
        int coins = 10 - (ripley.getGold()/10);

        scene.getGame().getOverlay().drawRectangle(
            303,680,200,130,Color.DARK_GRAY, 1,Color.WHITE
        );
        scene.getGame().getOverlay().drawAnimation(
            ripley.getAnimation(), 460, 820
        );
        scene.getGame().getOverlay().drawText(
            " Ripley Game! ",
            310, 830
        );
        scene.getGame().getOverlay().drawText(
            " Energy  ----> " + ripley.getHealth().getValue(),
            303, 790
        );
        scene.getGame().getOverlay().drawAnimation(
            energyAnimation, 380, 788
        );
        scene.getGame().getOverlay().drawText(
            " Ammo  ------> " + ripley.getFirearm().getAmmo(),
            303, 765
        );
        scene.getGame().getOverlay().drawAnimation(
            ammoAnimation, 360, 763
        );
        scene.getGame().getOverlay().drawText(
            " Gold  ------> " + ripley.getGold(),
            303, 740
        );
        scene.getGame().getOverlay().drawAnimation(
            goldAnimationStatic, 358, 738
        );
        scene.getGame().getOverlay().drawText(
            " Coins left -> " + coins,
            303, 715
        );
        scene.getGame().getOverlay().drawText(
            " Backpack  --> " + backpack,
            303, 690
        );
        scene.getGame().getOverlay().drawAnimation(
            backpackAnimation, 403, 688
        );
    }
    void lastNotification(Scene scene){
        Animation goldAnimation = new Animation("sprites/gold2.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley.getHealth().getValue() <= 0) {
            scene.getGame().getOverlay().drawRectangle(
                400,300,800,400,Color.DARK_GRAY, 1,Color.WHITE
            );

            scene.getGame().getOverlay().drawText(
                "You loose", 750,600
            );
            scene.getGame().getOverlay().drawText(
                "TRY AGAIN!", 748,550
            );
            scene.getGame().getOverlay().drawText(
                "'exit - escape'", 730,320
            );
        }
        if (ripley.getGold() == 100) {
            scene.getGame().getOverlay().drawRectangle(
                400,300,800,300,Color.SLATE, 1,Color.WHITE
            );
            scene.getGame().getOverlay().drawText(
                "You win", 760,500
            );
            scene.getGame().getOverlay().drawText(
                "GREAT JOB!", 748,470
            );
            scene.getGame().getOverlay().drawText(
                "'LET'S PLAY ONE MORE TIME!'", 650,320
            );
            scene.getGame().getOverlay().drawAnimation(
                goldAnimation, 794, 515
            );
        }
    }

}
