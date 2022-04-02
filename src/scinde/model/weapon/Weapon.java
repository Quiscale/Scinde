package scinde.model.weapon;

import java.util.List;

import scinde.model.world.World;

public abstract class Weapon {
	
	private List<WeaponAugment> augments;
	private boolean canBeAugmented;
	private WeaponStats stats;
	
	protected Weapon(boolean augmentable, WeaponStats stat)
	{
		canBeAugmented = augmentable;
		stats = stat;
	}
	
	public List<WeaponAugment> getAuments()
	{
		return augments;
	}
	
	public void addAugment(WeaponAugment augment)
	{
		if(canBeAugmented)
		{
			augment.apply(stats);
			augments.add(augment);
		}
	}
	
	public void removeAugment(WeaponAugment augment)
	{
		if(augments.contains(augment))
		{
			augment.revert(stats);
			augments.remove(augment);
		}
	}
	
	public void cleadAugment()
	{
		augments.clear();
	}
	
	public WeaponStats getStats()
	{
		return stats;
	}
	
	public abstract void use(World world);
}
