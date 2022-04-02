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
import scinde.utils.hitbox.CircleHitbox;
import scinde.utils.hitbox.CustomHitbox;
import scinde.utils.hitbox.HitBox;
import scinde.utils.hitbox.RectangularHitbox;

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
		if (object.has("hitboxgroups")) {
			JSONArray groups = object.getJSONArray("hitboxgroups");
			for (int i = 0; i < groups.length(); i++) {
				JSONArray hitboxes = groups.getJSONObject(i).getJSONArray("hitboxes");
				List<HitBox> hitboxGroup = new ArrayList<>();
				JSONObject position = groups.getJSONObject(i).getJSONObject("position");
				for (int j = 0; j < hitboxes.length(); j++) {
					String type = hitboxes.getJSONObject(j).getString("type");
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
						hitboxGroup.add(hitbox);
					}
					
				}
				HitBox complex = new CustomHitbox(hitboxGroup);
				complex.init();
				complex.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
				world.addHitbox(complex);
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
