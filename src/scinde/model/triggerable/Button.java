package scinde.model.triggerable;

import scinde.model.entity.EntityHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Button extends Triggerable{
	
	private boolean buttonState;

	protected Button(boolean defaultButtonState, HitBox box) {
		super(box);
		this.buttonState = defaultButtonState;
	}
	
	public abstract void OnButtonPush(World world, boolean buttonState);
	

	public void onTrigger(World word, EntityHolder holder)
	{
		this.buttonState = !this.buttonState;
		OnButtonPush(word, buttonState);
	}

}
