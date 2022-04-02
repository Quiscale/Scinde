package scinde.triggerable;

import scinde.registry.Registry;
import scinde.utils.hitbox.HitBox;

public class Triggerables {
	public static final TriggerProvider DOOR = Registry.register(Registry.TRIGGER, "door", (HitBox box)->new Door(box));
}
