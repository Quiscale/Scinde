package scinde.model.triggerable;

import scinde.model.entity.DoorHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class DoorButton extends Button{

	protected DoorButton(HitBox box) {
		super(false, box);
	}

	@Override
	public void OnButtonPush(World world, boolean buttonState) {
		this.linked.getHitbox().setEnabled(buttonState);
	}
	
	
}
