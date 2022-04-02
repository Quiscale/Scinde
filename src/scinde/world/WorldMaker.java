package scinde.world;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import scinde.entity.Entity;
import scinde.registry.Identifier;
import scinde.registry.Registry;
import scinde.utils.Position;
import scinde.utils.hitbox.HitBox;

public class WorldMaker {

	private InputStream levelStream;

	public WorldMaker(String levelName) {
		levelStream = WorldMaker.class.getResourceAsStream("/levels/" + levelName + ".json");
	}

	public World make() {
		World world = new World();// TODO : make world
		String json = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				json += line;
			}
		} catch (Exception e) {
			return world;
		}
		JSONObject object = new JSONObject(json);
		if (object.has("enemies")) {
			JSONArray enemies = object.getJSONArray("enemies");
			for (int i = 0; i < enemies.length(); i++) {
				JSONObject position = enemies.getJSONObject(i).getJSONObject("position");
				String id = enemies.getJSONObject(i).getString("id");
				Entity entity = Registry.ENTITY.get(new Identifier(id));
				if (position != null) {
					entity.setPosition(new Position(position.getInt("x"), position.getInt("y")));
				}
				world.spawnEntity(entity);
			}
		}
		if (object.has("player")) {
			JSONObject position = object.getJSONObject("player").getJSONObject("position");
			String id = "player";
			Entity entity = Registry.ENTITY.get(new Identifier(id));
			if (position != null) {
				entity.setPosition(new Position(position.getInt("x"), position.getInt("y")));
			}
			world.spawnEntity(entity);
		}
		if (object.has("hitboxgroups")) {
			JSONArray groups = object.getJSONArray("hitboxgroups");
			for (int i = 0; i < groups.length(); i++) {
				JSONArray hitboxes = groups.getJSONObject(i).getJSONArray("hitboxes");
				List<HitBox> hitboxGroup;
				for (int j = 0; j < hitboxes.length(); j++) {
					String type = hitboxes.getJSONObject(j).getString("type");
					JSONObject position = hitboxes.getJSONObject(j).getJSONObject("position");
				}
			}
		}
		return world;
	}
}
