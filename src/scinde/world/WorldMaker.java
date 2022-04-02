package scinde.world;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import scinde.entity.Entity;
import scinde.registry.Identifier;
import scinde.registry.Registry;
import scinde.utils.Position;
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
						System.out.println("WARN : unkownhitbox type " + type);
						break;
					}
					if(hitbox != null)
					{
						hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
						hitboxGroup.add(hitbox);
					}
					
				}
				HitBox complex = new CustomHitbox(hitboxGroup);
				world.addHitbox(complex);
			}
		}
		return world;
	}
}
