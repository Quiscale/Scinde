package scinde.model.weapon.projectile;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.enemies.Enemy;
import scinde.model.utils.Position;
import scinde.model.world.World;

public class Rail extends Projectile{

	protected Rail() {
		super(0, 0, 0.3);
	}

	@Override
	public float getDamage() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void onHit(World world, Position pos, EntityHolder self, EntityHolder other) {
		self.kill(world);
	}

	@Override
	public void onDeath(World world, Position pos, EntityHolder self) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(List<World> worlds, EntityHolder self) {
		self.setVelocityRaw(self.getVelocity().toUnit().mult(5));
	}
	
}
