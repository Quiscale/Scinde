package scinde.entity;

import scinde.registry.Registry;

public class Entities {
	public static final Entity PLAYER = Registry.register(Registry.ENTITY, "player", new Player());
}
