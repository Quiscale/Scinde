package scinde.registry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterators;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import scinde.entity.Entities;
import scinde.entity.Entity;
import scinde.entity.EntityBuilder;
import scinde.triggerable.TriggerProvider;
import scinde.triggerable.Triggerables;

public abstract class Registry<T> implements Iterable<T>{
	
	protected BiMap<RegistryKey<? extends T>, T> values;
	protected Map<Identifier, T> idEntries;
	private boolean erraseDuplicateKey;
	private RegistryKey<? extends Registry<T>> key;
	private ObjectList<T> rawIdToEntry;
	private static int id = 0;
	
	protected Registry(RegistryKey<? extends Registry<T>> key)
	{
		this.values = HashBiMap.create();
		erraseDuplicateKey = true;
		this.key = key;
		idEntries = new HashMap<>();
		rawIdToEntry = new ObjectArrayList<T>(256);
	}
	
	public Registry<T> preventDuplicateKeyErase()
	{
		erraseDuplicateKey = false;
		return this;
	}

	@Override
	public Iterator<T> iterator() {
		return Iterators.filter(this.rawIdToEntry.iterator(), Objects::nonNull);
	}
	
	public RegistryKey<? extends Registry<T>> getKey()
	{
		return key;
	}
			
	public Set<Identifier> getIds()
	{
		return idEntries.keySet();
	}
	
	public <R extends T> R add(RegistryKey<? extends T> key2, R registry)
	{
		if((values.containsKey(key2) && erraseDuplicateKey) || !values.containsKey(key2))
		{
			values.put(key2, registry);
			idEntries.put(key2.getValue(), registry);
			this.rawIdToEntry.size(Math.max(this.rawIdToEntry.size(), id + 1));
	        this.rawIdToEntry.set(id, registry);
	        id++;
		}
		return registry;
	}
	
	public String toString()
	{
		return "Registry["+this.key+"]";
	}
	
	public abstract T get(Identifier id);	
	public abstract Identifier getId(T value);
	
	
	private static Map<Identifier, DefaultProvider<?>> DEFAULT_ENTRIES = new HashMap<>();
	
	public static final Identifier ROOT_KEY = new Identifier("root");
	public static final RegistryKey<Registry<EntityBuilder>> ENTITY_KEY = Registry.createRegistryKey("entity");
	public static final RegistryKey<Registry<TriggerProvider>> TRIGGER_KEY = Registry.createRegistryKey("trigger");
	
	public static final Registry<Registry<?>> ROOT = new SimpleRegistry<Registry<?>>(Registry.createRegistryKey("root"));
	public static final SimpleRegistry<EntityBuilder> ENTITY = Registry.create(ENTITY_KEY, ()->Entities.PLAYER);
	public static final SimpleRegistry<TriggerProvider> TRIGGER = Registry.create(TRIGGER_KEY, ()->Triggerables.DOOR);
	
	private static <T> RegistryKey<Registry<T>> createRegistryKey(String id)
	{
		return RegistryKey.ofRegistry(new Identifier(id));
	}
	
	private static <T> DefaultedRegistry<T> create(RegistryKey<? extends Registry<T>> key, String defaultId, DefaultProvider<T> provider)
	{
		return create(key, new DefaultedRegistry<T>(key, new Identifier(defaultId)), provider);
	}
	
	@SuppressWarnings("unused")
	private static <T> SimpleRegistry<T> create(RegistryKey<? extends Registry<T>> key, DefaultProvider<T> provider)
	{
		return create(key, new SimpleRegistry<T>(key), provider);
	}
	
	public static <T, R extends Registry<T>> R create(RegistryKey<? extends Registry<T>> key, R registry, DefaultProvider<T> provider)
	{
		Registry<Registry<?>> root = ROOT;
		DEFAULT_ENTRIES.put(key.getValue(), provider);
		return root.add(key, registry);
	}
	
	public static <T> T register(Registry<T> registry, String id, T value)
	{
		return register(registry, RegistryKey.of(registry.getKey().getValue(), new Identifier(id)), value);
	}
	
	public static <T> T register(Registry<T> registry, Identifier id, T value)
	{
		return register(registry, RegistryKey.of(registry.getKey().getValue(), id), value);
	}
	
	public static <T> T register(Registry<T> registry, RegistryKey<T> id, T value)
	{
		return registry.add(id, value);
	}
	
	private static void validate(Registry<?> registry)
	{
		if(registry.getIds().isEmpty())
		{
			System.out.println("WARN : registry "+registry.getKey()+" was empty");
		}
		if(registry instanceof DefaultedRegistry<?> defaulted)
		{
			if(defaulted.getDefaultValue() == null)
			{
				System.out.println("WARN : defaulted registry "+defaulted.getKey()+" has no default element linked to its default id");
			}
		}
	}

	private static void setupRegistries() {
		for(Map.Entry<Identifier, DefaultProvider<?>> entry : DEFAULT_ENTRIES.entrySet())
		{
			if(entry.getValue().provide() == null)
			{
				System.out.println("Impossible to boot registry "+entry.getKey());
			}
		}
		for(Registry<?> registry : ROOT)
		{
			validate(registry);
		}
	}
	
	
	static {
		setupRegistries();
	}
}
