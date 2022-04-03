package scinde.model.entity;


import scinde.model.utils.hitbox.RectangularHitbox;

public class DoorHolder extends EntityHolder{

	public DoorHolder(Door entity) {
		super(entity);
		if(entity.isHororizontal())
			this.hitbox = new RectangularHitbox(161, 44);
		else
			this.hitbox = new RectangularHitbox(44, 161);
		this.hitbox.init();
			
	}

}
