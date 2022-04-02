package scinde.model.triggerable;

import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class OneWayButton extends Button{

	protected OneWayButton(boolean defaultButtonState, HitBox box) {
		super(defaultButtonState, box);
	}	

	public void onTrigger(World word)
	{
		super.onTrigger(word);
		this.trigger.setEnabled(false);
	}

}
