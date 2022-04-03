package scinde.model.triggerable;

import scinde.model.registry.Registry;
import scinde.model.utils.hitbox.HitBox;

public class Triggerables {
	public static final TriggerProvider DOOR_BUTTON = Registry.register(Registry.TRIGGER, "door_btn", (HitBox box)->new DoorButton(box));
	public static final TriggerProvider UNLOCK_BLASTER = Registry.register(Registry.TRIGGER, "unlock_blaster", (HitBox box)->new UnlockBlasterTrigger(box));
	public static final TriggerProvider COCKPIT_LAUNCH = Registry.register(Registry.TRIGGER, "cockpit_launch", (HitBox box)->new CockpitTriggerLaunch(box));
	public static final TriggerProvider ONE_WAY_DOOR_BUTTON = Registry.register(Registry.TRIGGER, "one_way_door_btn", (HitBox box)->new OneWayDoorButton(box));
}
