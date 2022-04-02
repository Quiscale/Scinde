package scinde.entity;

import scinde.registry.Registry;

public class Entities {
	public static final EntityBuilder PLAYER = Registry.register(Registry.ENTITY, "player", ()->new Player());
	public static final EntityBuilder MOULA = Registry.register(Registry.ENTITY, "moula", ()->new Moula());
}
