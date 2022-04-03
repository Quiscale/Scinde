package scinde.model.entity;

import java.util.ArrayList;
import java.util.List;

import scinde.model.entity.player.Player;
import scinde.model.utils.Position;
import scinde.model.weapon.Weapon;
import scinde.model.world.World;

public class PlayerHolder extends EntityHolder{
	
	private List<Weapon> unlockedLeft;
	private List<Weapon> unlockedRight;
	
	private Weapon left;
	private Weapon right;

	public PlayerHolder(Player entity) {
		super(entity);

		left = null;
		right = null;
		
		unlockedLeft = new ArrayList<>();
		unlockedRight = new ArrayList<>();
	}
	
	public void equipNextLeft()
	{
		if(!unlockedLeft.isEmpty())
		{
			if(left == null)
			{
				left = unlockedLeft.get(0);
			}
			else
			{
				if(unlockedLeft.indexOf(left)+1 >= unlockedLeft.size())
				{
					left = unlockedLeft.get(0);
				}
				else
				{
					left = unlockedLeft.get(unlockedLeft.indexOf(left)+1);
				}
			}
		}
	}

	
	public void equipNextRight()
	{
		if(!unlockedRight.isEmpty())
		{
			if(right == null)
			{
				right = unlockedRight.get(0);
			}
			else
			{
				if(unlockedRight.indexOf(right)+1 >= unlockedRight.size())
				{
					right = unlockedRight.get(0);
				}
				else
				{
					right = unlockedRight.get(unlockedRight.indexOf(right)+1);
				}
			}
		}
	}
	
	public void useLeft(World world, Position target)
	{
		left.use(world, target, this);
	}
	
	public void useRight(World world, Position target)
	{
		left.use(world, target, this);
	}
	
	public void unlockLeft(Weapon weapon)
	{
		if(!unlockedLeft.contains(weapon))
		{
			unlockedLeft.add(weapon);
		}
	}
	
	public void unlockLRight(Weapon weapon)
	{
		if(!unlockedRight.contains(weapon))
		{
			unlockedRight.add(weapon);
		}
	}
	
	public void updateWeapons(World left, World right)
	{
		if(this.left != null)
			this.left.update(List.of(left));
		if(this.right != null)
			this.right.update(List.of(right));
	}

}
