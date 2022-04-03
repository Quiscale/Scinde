package scinde.model.triggerable;

import scinde.Main;
import scinde.model.entity.EntityHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class CockpitTriggerLaunch extends Triggerable{

	protected CockpitTriggerLaunch(HitBox box) {
		super(box);
	}

	@Override
	public void onTrigger(World word, EntityHolder entity) {

		Main.GAME.startSuperluminal();
		this.trigger.setEnabled(false);
	}

}
