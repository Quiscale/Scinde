package scinde.model.level;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONObject;

import scinde.model.entity.Entity;
import scinde.model.entity.Player;
import scinde.model.registry.Identifier;
import scinde.model.registry.Registry;
import scinde.model.utils.Position;
import scinde.model.world.World;
import scinde.model.world.WorldMaker;

public class LevelMaker {


	private InputStream levelStream;
	private String levelName;
	
	public LevelMaker(String levelName)
	{
		this.levelName = levelName;
		levelStream = WorldMaker.class.getResourceAsStream("/levels/" + levelName + ".json");
	}
	
	public Level make() throws Exception
	{
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
		if(!object.has("left") || !object.has("right") || !object.has("player"))
		{
			throw new Exception("Invalid level object for level "+levelName);
		}
		World left = new WorldMaker().make(object.getJSONObject("left"));
		World right = new WorldMaker().make(object.getJSONObject("right"));
		
		JSONObject position = object.getJSONObject("player").getJSONObject("position");
		String id = "player";
		Entity entity = Registry.ENTITY.get(new Identifier(id)).provide();
		if (position != null) {
			entity.setPosition(new Position(position.getFloat("x"), position.getFloat("y")));
		}
		if(entity instanceof Player player)
		{
			return new Level(levelName, player, new ArrayList<>(), left, right);
		}
		else
		{
			throw new Exception("Entity of type player not found on the level");
		}
	}
}
