package scinde.view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;

public class Loader {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	private Map<String, Object> resources = new HashMap<>();
	private String rootPath;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public Loader(String rootPath) {
		super();
		
		this.rootPath = rootPath;
		
		this.loadJar();
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	public void loadJar() {
		System.out.println("[loader] load fonts");
		
		String[] pathsTexture = new String[] {
				"fond-espace",
				"fond-supraluminique",
				"vaisseau",
				"character",
				"bouton-menu"
		};
		String[] pathsSound = new String[] {
				"Fight_Music",
				"Title_Screen_v2"
		};

		ClassLoader cl = Loader.class.getClassLoader();
		for(String path : pathsTexture) {
			this.resources.put(path, new Image(cl.getResourceAsStream(this.rootPath + "texture/" + path + ".png")));
		}
		for(String path : pathsSound) {
			this.resources.put(path, new Media(cl.getResource(this.rootPath + "sound/" + path + ".mp3").toExternalForm()));
		}
		
		System.out.println("Assets loaded");
	}
	
	public Resource get(String name) {
		
		System.out.println("[loader] get : " + name);
		
		Optional<Entry<String, Object>> result = resources.entrySet()
				.stream()
				.filter( k -> k.getKey().endsWith(name))
				.findFirst();
		
		if(result.isPresent())
			return new Resource(result.get().getValue());
		else
			return null;
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Class
	// ////////////////////////////////////////////////////////////////////////
	
	public static class Resource {
		
		private Object object;
		
		public Resource(Object object) {
			this.object = object;
		}
		
		public Image asImage() {
			return (Image) object;
		}
		
		public Font asFont() {
			return (Font) object;
		}
		
		public Media asMedia() {
			return (Media) object;
		}
		
	}
}
