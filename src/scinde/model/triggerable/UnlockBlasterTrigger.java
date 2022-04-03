package scinde.model.triggerable;

import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.weapon.Weapons;
import scinde.model.world.World;

public class UnlockBlasterTrigger extends Triggerable
{

	protected UnlockBlasterTrigger(HitBox box) {
		super(box);
	}

	@Override
	public void onTrigger(World word, EntityHolder entity) {
		if(entity instanceof PlayerHolder player)
		{
			System.out.println("raygun !");
			player.unlockLeft(Weapons.RAYGUN);
			player.equipNextLeft();
		}
	}

}
