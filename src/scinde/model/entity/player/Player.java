package scinde.model.entity.player;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.enemies.Enemy;
import scinde.model.level.LevelManager;
import scinde.model.utils.Position;
import scinde.model.world.World;

public class Player extends Entity{

	public Player() {
		super(3f, true, 0.9f, 0.3, 40);
	}

	@Override
	public void onHit(World world, Position pos, EntityHolder self, EntityHolder other) {
		System.out.println("ouch!");
		self.setInvincibleFor(3000);
	}

	@Override
	public void onDeath(World world, Position pos, EntityHolder self) {
		LevelManager.instance.reloadCurrent();
	}

	@Override
	public void onUpdate(List<World> worlds, EntityHolder self) {
		
	}

	@Override
	public float getDamage() {
		//TODO : damage depends on the weapon
		return 0;
	}

	@Override
	protected boolean canDamage(Entity entity) {
		return false;
	}
}
