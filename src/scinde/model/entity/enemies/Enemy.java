package scinde.model.entity.enemies;

import scinde.model.entity.Entity;
import scinde.model.entity.player.Player;
import scinde.model.registry.Registry;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.HitBox;

public abstract class Enemy extends Entity {

	private float damage;

	protected Enemy(float lifePoints, float damage, float velocityUnit, float bouncyness, float hitboxSize) {
		super(lifePoints, true, bouncyness, velocityUnit, hitboxSize);
		this.damage = damage;
	}

	@Override
	public float getDamage() {
		//TODO : damage depends on the weapon
		return damage;
	}

	@Override
	protected boolean canDamage(Entity entity) {
		return entity instanceof Player;
	}
	
	public String toString()
	{
		return ""+Registry.ENEMY.getId(this);
	}
}
