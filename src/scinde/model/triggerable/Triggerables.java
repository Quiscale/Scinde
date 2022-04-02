package scinde.model.triggerable;

import scinde.model.registry.Registry;
import scinde.model.utils.hitbox.HitBox;

public class Triggerables {
	public static final TriggerProvider DOOR = Registry.register(Registry.TRIGGER, "door", (HitBox box)->new Door(box));
}
