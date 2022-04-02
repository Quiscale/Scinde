package scinde.model.entity.enemies;

import scinde.model.registry.Registry;

public class Enemies {
	public static final EnemyBuilder MOULA = Registry.register(Registry.ENEMY, "moula", ()->new Moula());
}
