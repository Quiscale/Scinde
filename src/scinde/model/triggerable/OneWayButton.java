package scinde.model.triggerable;

import scinde.model.entity.EntityHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class OneWayButton extends Button{

	protected OneWayButton(boolean defaultButtonState, HitBox box) {
		super(defaultButtonState, box);
	}	

	public void onTrigger(World word, EntityHolder entity)
	{
		super.onTrigger(word, entity);
		this.trigger.setEnabled(false);
	}

}
