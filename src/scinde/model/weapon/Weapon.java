package scinde.model.weapon;

import java.util.List;

import scinde.controller.UpdateTimer;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.utils.IUpdatable;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.weapon.projectile.Projectile;
import scinde.model.weapon.projectile.Projectiles;
import scinde.model.world.World;
import scinde.view.IHM;

public abstract class Weapon implements IUpdatable {

	private double reloadRate;
	private double reloadTracker;
	private double fireRate;
	private double fireTracker;
	private int magazineSize;
	private int remainingBullets;

	protected Weapon(double reloadRate, double fireRate, int magazineSize) {
		this.reloadRate = reloadRate;
		reloadTracker = 0;
		this.fireRate = fireRate;
		fireTracker = 0;
		this.magazineSize = magazineSize;
		remainingBullets = magazineSize;
	}

	public abstract EntityHolder getProjectile();

	public void use(World world, Position target, PlayerHolder user) {
		if (reloadTracker <= 0 && fireTracker <= 0) {
			EntityHolder projectile = getProjectile();
			projectile.setVelocityRaw(Velocity.createVector(user.getPos(), target).toUnit());
			Velocity spawn = projectile.getVelocity().mult(projectile.getEntity().getHitboxSize() + user.getEntity().getHitboxSize()+1);
			projectile.setPosition(new Position(
					user.getPos().getX() + spawn.getX(),
					user.getPos().getY() + spawn.getY()));
			world.spawnEntity(projectile);
			IHM.PANE.SHIP.showHitbox(projectile);
			System.out.println("use rail " + target);
			fireTracker = fireRate;
			remainingBullets --;
			if(remainingBullets <= 0)
			{
				reloadTracker = reloadRate;
				remainingBullets = magazineSize;
			}
		}
		else {
			System.out.println("wait");
		}
	}

	public void update(List<World> worlds) {
		if(reloadTracker > 0)
			reloadTracker -= UpdateTimer.ELAPSED_TIME;
		if(fireTracker > 0)
			fireTracker -= UpdateTimer.ELAPSED_TIME;
	}
}
