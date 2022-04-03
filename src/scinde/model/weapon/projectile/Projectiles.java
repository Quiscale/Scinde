package scinde.model.weapon.projectile;

import scinde.model.registry.Registry;

public class Projectiles {
	public static final Projectile RAIL = Registry.register(Registry.PROJECTILE, "rail", new Rail());
}
