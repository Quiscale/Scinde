package scinde.model.weapon;

import scinde.model.registry.Registry;

public class Weapons {
	public static final Weapon RAYGUN = Registry.register(Registry.WEAPON, "railgun", new Raygun());
}
