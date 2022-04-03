package scinde.model.entity.enemies;

import scinde.model.registry.Registry;

public class Enemies {
	public static final Enemy MOULA = Registry.register(Registry.ENEMY, "moule_a", new Moula());
}
