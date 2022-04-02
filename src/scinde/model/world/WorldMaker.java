package scinde.model.world;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import scinde.model.entity.Entity;
import scinde.model.registry.Identifier;
import scinde.model.registry.Registry;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.CustomHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.utils.hitbox.RectangularHitbox;

public class WorldMaker {

	public World make(JSONObject object) {
		World world = new World();// TODO : make world
		if (object.has("enemies")) {
			JSONArray enemies = object.getJSONArray("enemies");
			for (int i = 0; i < enemies.length(); i++) {
				JSONObject position = enemies.getJSONObject(i).getJSONObject("position");
				String id = enemies.getJSONObject(i).getString("id");
				Entity entity = Registry.ENTITY.get(new Identifier(id)).provide();
				if (position != null) {
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
				}
				world.spawnEntity(entity);
			}
		}
		if (object.has("hitboxes")) {
			JSONArray hitboxes = object.getJSONArray("hitboxes");
			List<HitBox> hitboxGroup = new ArrayList<>();
			for (int j = 0; j < hitboxes.length(); j++) {
				String type = hitboxes.getJSONObject(j).getString("type");
				JSONObject position = hitboxes.getJSONObject(j).getJSONObject("position");
				HitBox hitbox = null;
				switch (type) {
				case "circle":
					hitbox = new CircleHitbox(hitboxes.getJSONObject(j).getFloat("radius"));
					break;
				case "rectangle":
					float width = hitboxes.getJSONObject(j).getFloat("width");
					float height = hitboxes.getJSONObject(j).getFloat("height");
					hitbox = new RectangularHitbox(width, height);
					break;
				default:
					System.out.println("WARN : unkown hitbox type " + type);
					break;
				}
				if(hitbox != null)
				{
					hitbox.init();
					if(hitboxes.getJSONObject(j).has("angle"))
					{
						hitbox.rotate(hitboxes.getJSONObject(j).getFloat("angle"));
					}
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					System.out.println(hitbox);
					List<HitBox> merged = new ArrayList<>();
					merged.add(hitbox);
					for(HitBox box : hitboxGroup)
					{
						if(hitbox.overlap(box))
						{
							merged.add(box);
						}
					}
					for(HitBox box : merged)
					{
						if(hitboxGroup.contains(box))
							hitboxGroup.remove(box);
					}
					if(merged.size() == 1)
					{
						HitBox box = merged.get(0);
						hitboxGroup.add(box);
					}
					else
					{
						HitBox box = new CustomHitbox(merged);
						box.init();
						hitboxGroup.add(box);
					}
				}				
			}
			System.out.println(hitboxGroup.size());
			for(HitBox box : hitboxGroup)
			{
				world.addHitbox(box);
			}
		}
		if (object.has("triggerables")) {
			JSONArray triggers = object.getJSONArray("triggerables");
			for (int i = 0; i < triggers.length(); i++) {
				JSONObject position = triggers.getJSONObject(i).getJSONObject("position");
				JSONObject hitboxObj = triggers.getJSONObject(i).getJSONObject("trigger");
				String id = triggers.getJSONObject(i).getString("id");
				HitBox hitbox = null;
				switch (hitboxObj.getString("type")) {
				case "circle":
					hitbox = new CircleHitbox(hitboxObj.getFloat("radius"));
					break;
				case "rectangle":
					float width = hitboxObj.getFloat("width");
					float height = hitboxObj.getFloat("height");
					hitbox = new RectangularHitbox(width, height);
					break;
				default:
					System.out.println("WARN : unkown hitbox type " + hitboxObj.getString("type"));
					break;
				}
				if(hitbox != null)
				{
					hitbox.init();
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					world.addTriggerable(Registry.TRIGGER.get(new Identifier(id)).provide(hitbox));
				}
			}
		}
		return world;
	}
}
