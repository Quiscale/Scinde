package scinde.model.triggerable;

import scinde.model.utils.hitbox.HitBox;

public interface TriggerProvider {
	public Triggerable provide(HitBox trigger);
}
