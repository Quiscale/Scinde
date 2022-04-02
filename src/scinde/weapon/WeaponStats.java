package scinde.weapon;

public class WeaponStats {
	private float fireRate;
	private float damage;
	private int magazineSize;
	
	public static final int UNLIMITED_MAGAZINE = -1;

	public WeaponStats(float fireRate, float damage, int magazineSize) {
		this.fireRate = fireRate;
		this.damage = damage;
		this.magazineSize = magazineSize;
	}

	public float getFireRate() {
		return fireRate;
	}

	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public int getMagazineSize() {
		return magazineSize;
	}

	public void setMagazineSize(int magazineSize) {
		this.magazineSize = magazineSize;
	}
}
