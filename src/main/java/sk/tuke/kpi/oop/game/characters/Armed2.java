package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public interface Armed2 extends Actor {
    Firearm getFirearm();
    void setFirearm(Firearm firearm);

}
