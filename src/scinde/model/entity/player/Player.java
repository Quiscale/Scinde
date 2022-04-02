package scinde.model.entity.player;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.enemies.Enemy;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.weapon.Weapon;
import scinde.model.world.World;

public class Player extends Entity{
	
	private Weapon left;
	private Weapon right;

	public Player() {
		super(3f, true, 2, 0.3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onHit(World world, Entity other) {
		
	}

	@Override
	public void onDeath(World world) {
		//what do we do ?
	}

	@Override
	public void onUpdate(List<World> worlds) {
		
	}

	@Override
	public HitBox provideHitbox() {
		return new CircleHitbox(10);
	}

	@Override
	public float getDamage() {
		//TODO : damage depends on the weapon
		return 0;
	}

	@Override
	protected boolean canDamage(Entity entity) {
		return entity instanceof Enemy;
	}
}
