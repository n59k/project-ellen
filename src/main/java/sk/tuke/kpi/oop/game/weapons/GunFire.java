package sk.tuke.kpi.oop.game.weapons;

public class GunFire extends Firearm {
    public GunFire(int initialAmmo, int maxAmmo) {
        super(initialAmmo, maxAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Gun2();
    }
}
