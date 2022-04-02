package scinde.model.triggerable;

import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class OneWayDoorButton extends OneWayButton{

	protected OneWayDoorButton(HitBox box) {
		super(false, box);
	}

	@Override
	public void OnButtonPush(World world, boolean buttonState) {
		
	}

}
