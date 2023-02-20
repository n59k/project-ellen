package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int ripAmmo;
    private int maxRipAmmo;


    public Firearm(int initAmmo, int maxAmmo) {
        ripAmmo = initAmmo;
        this.maxRipAmmo = maxAmmo;
    }

    public Firearm(int initAmmo) {
        ripAmmo = initAmmo;
        this.maxRipAmmo = initAmmo;
    }

    public int getAmmo() {
        return ripAmmo;
    }

    public void reload(int newAmmo) {
        ripAmmo = ripAmmo + newAmmo;
        if (this.ripAmmo > this.maxRipAmmo)
            ripAmmo = this.maxRipAmmo;
    }
    public void case1(int newAmmo) {
        ripAmmo = newAmmo;
    }

    public Fireable fire() {
        if (ripAmmo >= 1) {
            ripAmmo = ripAmmo - 1;
            return createBullet();
        } else if (ripAmmo == 0) {
            return null;
        } else {
            return null;
        }
    }

    protected abstract Fireable createBullet();
}
