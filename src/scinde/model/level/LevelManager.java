package scinde.model.level;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONObject;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.entity.player.Player;
import scinde.model.registry.Identifier;
import scinde.model.registry.Registry;
import scinde.model.utils.Position;
import scinde.model.world.World;
import scinde.model.world.WorldMaker;

public class LevelManager {

	private InputStream levelStream;
	private AbstractLevel currentLevel;

	public static final LevelManager instance = new LevelManager();

	private LevelManager() {
	}

	public AbstractLevel getCurrentLevel() {
		return currentLevel;
	}

	public void reloadCurrent() {
		try {
			load(currentLevel.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AbstractLevel level() {
		return instance.currentLevel;
	}

	public void load(String levelName) throws Exception {
		levelStream = WorldMaker.class.getResourceAsStream("/assets/scinde/levels/" + levelName + ".json");
		String json = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				json += line;
			}
		} catch (Exception e) {
			throw e;
		}
		JSONObject object = new JSONObject(json);
		if (object.has("left") && object.has("right")) {
			World left = new WorldMaker().make(object.getJSONObject("left"));
			World right = new WorldMaker().make(object.getJSONObject("right"));

			JSONObject position = object.getJSONObject("player").getJSONObject("position");
			String id = "player";
			Entity data = Registry.ENTITY.get(new Identifier(id));
			if (data instanceof Player player) {
				PlayerHolder entity = new PlayerHolder(player);
				if (position != null) {
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
				}
				currentLevel = new DualLevel(levelName, entity, new ArrayList<>(), left, right);
			}
		} else if(object.has("world")){
			World world = new WorldMaker().make(object.getJSONObject("world"));

			JSONObject position = object.getJSONObject("player").getJSONObject("position");
			String id = "player";
			Entity data = Registry.ENTITY.get(new Identifier(id));
			if (data instanceof Player player) {
				PlayerHolder entity = new PlayerHolder(player);
				if (position != null) {
					entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
				}
				currentLevel = new SingleLevel(levelName, world, entity);
			}
		}
	}
}
