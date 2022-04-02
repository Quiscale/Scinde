package scinde.model.entity;

import scinde.model.entity.enemies.Moula;
import scinde.model.entity.player.Player;
import scinde.model.registry.Registry;

public class Entities {
	public static final EntityBuilder PLAYER = Registry.register(Registry.ENTITY, "player", ()->new Player());
	public static final EntityBuilder MOULA = Registry.register(Registry.ENTITY, "moula", ()->new Moula());
}
