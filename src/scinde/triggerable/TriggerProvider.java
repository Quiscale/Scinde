package scinde.triggerable;

import scinde.utils.hitbox.HitBox;

public interface TriggerProvider {
	public Triggerable provide(HitBox trigger);
}
