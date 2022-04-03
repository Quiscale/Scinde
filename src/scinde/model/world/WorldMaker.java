package scinde.model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import scinde.model.entity.Door;
import scinde.model.entity.DoorHolder;
import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.enemies.Enemy;
import scinde.model.entity.enemies.OpenPatternFollower;
import scinde.model.entity.enemies.PatternFollower;
import scinde.model.registry.Identifier;
import scinde.model.registry.Registry;
import scinde.model.triggerable.Triggerable;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.CustomHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.utils.hitbox.PolygonHitbox;
import scinde.model.utils.hitbox.RectangularHitbox;

public class WorldMaker {

	public World make(JSONObject object) {
		Map<Integer, EntityHolder> registry = new HashMap<>();
		World world = new World();// TODO : make world
		if(object.has("entities"))
		{
			JSONArray entities = object.getJSONArray("entities");
			for(int i = 0; i < entities.length(); i++)
			{
				JSONObject position = entities.getJSONObject(i).getJSONObject("position");
				String id = entities.getJSONObject(i).getString("name");
				Entity data = Registry.ENTITY.get(new Identifier(id));
				if(data instanceof Door door)
				{
					EntityHolder entity = new DoorHolder(door);
					registry.put(entities.getJSONObject(i).getInt("id"), entity);
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
					System.out.println(entity.getHitbox());
					world.spawnEntity(entity);
				}
				else
				{
					EntityHolder entity = new EntityHolder(data);
					registry.put(entities.getJSONObject(i).getInt("id"), entity);
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
					world.spawnEntity(entity);
				}
			}
		}
		if (object.has("enemies")) {
			JSONArray enemies = object.getJSONArray("enemies");
			for (int i = 0; i < enemies.length(); i++) {
				JSONObject position = enemies.getJSONObject(i).getJSONObject("position");
				String id = enemies.getJSONObject(i).getString("name");
				Enemy enemy = Registry.ENEMY.get(new Identifier(id));
				EntityHolder entity = new EntityHolder(enemy);
				registry.put(enemies.getJSONObject(i).getInt("id"), entity);
				if (position != null) {
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
				}
				boolean startReverse = false;
				if(enemies.getJSONObject(i).has("startreverse"))
				{
					startReverse = enemies.getJSONObject(i).getBoolean("startreverse");
				}
				if(enemies.getJSONObject(i).has("pattern"))
				{
					JSONObject pattern = enemies.getJSONObject(i).getJSONObject("pattern");
					boolean isLooping = true;
					if(pattern.has("islooping"))
					{
						isLooping = pattern.getBoolean("islooping");
					}
					if(!isLooping)
					{
						startReverse = false;
					}
					JSONArray points = pattern.getJSONArray("points");
					if(points.length() >= 2)
					{
						List<Position> enemyPattern = new ArrayList<>();
						for (int j = 0; j < points.length(); j++) {
							enemyPattern.add(new Position(points.getJSONObject(j).getFloat("x"), points.getJSONObject(j).getFloat("y")));
						}
						PatternFollower patternFollower = null;
						if(isLooping)
							patternFollower = new PatternFollower(entity, enemyPattern);
						else
							patternFollower = new OpenPatternFollower(entity, enemyPattern);
						
						entity.setPattern(patternFollower, startReverse);
					}
				}
				world.spawnEntity(entity);
			}
		}
		if (object.has("hitboxes")) {
			JSONArray hitboxes = object.getJSONArray("hitboxes");
			List<HitBox> hitboxGroup = new ArrayList<>();
			for (int j = 0; j < hitboxes.length(); j++) {
				String type = hitboxes.getJSONObject(j).getString("type");
				HitBox hitbox = null;
				switch (type) {
				case "circle":
					JSONObject position = hitboxes.getJSONObject(j).getJSONObject("position");
					hitbox = new CircleHitbox(hitboxes.getJSONObject(j).getFloat("radius"));
					hitbox.init();
					if(hitboxes.getJSONObject(j).has("angle"))
					{
						hitbox.rotate(hitboxes.getJSONObject(j).getFloat("angle"));
					}
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					break;
				case "rectangle":
					position = hitboxes.getJSONObject(j).getJSONObject("position");
					float width = hitboxes.getJSONObject(j).getFloat("width");
					float height = hitboxes.getJSONObject(j).getFloat("height");
					hitbox = new RectangularHitbox(width, height);
					hitbox.init();
					if(hitboxes.getJSONObject(j).has("angle"))
					{
						hitbox.rotate(hitboxes.getJSONObject(j).getFloat("angle"));
					}
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					break;
				case "polygon":
					List<Position> points = new ArrayList<>();
					JSONArray opoints = hitboxes.getJSONObject(j).getJSONArray("points");
					for(int i = 0; i<opoints.length(); i++)
					{
						points.add(new Position(opoints.getJSONObject(i).getFloat("x"), opoints.getJSONObject(i).getFloat("y")));
					}		
					hitbox = new PolygonHitbox(points);
					hitbox.init();
					break;
				default:
					System.out.println("WARN : unkown hitbox type " + type);
					break;
				}
				if(hitbox != null)
				{
					List<HitBox> customs = new ArrayList<>();
					customs.add(hitbox);
					for(HitBox box : hitboxGroup)
					{
						if(box.overlap(hitbox))
						{
							customs.add(box);
						}
					}
					if(customs.size() == 1)
						hitboxGroup.add(hitbox);
					else
					{
						CustomHitbox custom = new CustomHitbox(customs);
						custom.init();
						hitboxGroup.add(custom);
						for(HitBox box : customs)
						{
							if(hitboxGroup.contains(box))
							{
								hitboxGroup.remove(box);
							}
						}
					}
				}
			}
			for(HitBox box : hitboxGroup)
			{
				world.addHitbox(box);
			}
		}
		if (object.has("triggerables")) {
			JSONArray triggers = object.getJSONArray("triggerables");
			for (int i = 0; i < triggers.length(); i++) {
				JSONObject hitboxObj = triggers.getJSONObject(i).getJSONObject("trigger");
				String id = triggers.getJSONObject(i).getString("id");
				HitBox hitbox = null;
				switch (hitboxObj.getString("type")) {
				case "circle":
					JSONObject position = triggers.getJSONObject(i).getJSONObject("position");
					hitbox = new CircleHitbox(hitboxObj.getFloat("radius"));
					hitbox.init();
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					break;
				case "rectangle":
					position = triggers.getJSONObject(i).getJSONObject("position");
					float width = hitboxObj.getFloat("width");
					float height = hitboxObj.getFloat("height");
					hitbox = new RectangularHitbox(width, height);
					hitbox.init();
					hitbox.moveTo(new Position(position.getFloat("x"), position.getFloat("y")));
					break;
				case "polygon":
					List<Position> points = new ArrayList<>();
					JSONArray opoints = hitboxObj.getJSONArray("points");
					for(int j = 0; j<opoints.length(); j++)
					{
						points.add(new Position(opoints.getJSONObject(j).getFloat("x"), opoints.getJSONObject(j).getFloat("y")));
					}		
					hitbox = new PolygonHitbox(points);
					hitbox.init();
				default:
					System.out.println("WARN : unkown hitbox type " + hitboxObj.getString("type"));
					break;
				}
				if(hitbox != null)
				{
					Triggerable trigger = Registry.TRIGGER.get(new Identifier(id)).provide(hitbox);

					if(triggers.getJSONObject(i).has("link"))
					{
						trigger.setLinked(registry.get(triggers.getJSONObject(i).getInt("link")));
					}
					world.addTriggerable(trigger);
				}
			}
		}
		return world;
	}
}
