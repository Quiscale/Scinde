package scinde.model.entity;

import scinde.model.entity.player.Player;
import scinde.model.registry.Registry;

public class Entities {
	public static final Entity PLAYER = Registry.register(Registry.ENTITY, "player", new Player());
	public static final Entity HORIZONTAL_DOOR = Registry.register(Registry.ENTITY, "horizontal_door", new HorizontalDoor());
	public static final Entity VERTICAL_DOOR = Registry.register(Registry.ENTITY, "vertical_door", new VerticalDoor());
}
