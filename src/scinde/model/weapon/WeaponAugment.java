package scinde.model.weapon;

public interface WeaponAugment {
	public void apply(WeaponStats stats);
	public void revert(WeaponStats stats);
}
