package fr.uge.myproject.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemDatabase {

	private static final Map<String, String> itemBase = new HashMap<>();

	static {

		itemBase.put("ICE", "biome");
		itemBase.put("LAVA", "biome");
		itemBase.put("WATER", "biome");
		itemBase.put("BABA", "character");
		itemBase.put("BADBAD", "character");
		itemBase.put("FOFO", "character");
		itemBase.put("IT", "character");
		itemBase.put("ALGAE", "deco");
		itemBase.put("CLOUD", "deco");
		itemBase.put("FLOWER", "deco");
		itemBase.put("FOLIAGE", "deco");
		itemBase.put("GRASS", "deco");
		itemBase.put("LADDER", "deco");
		itemBase.put("LILY", "deco");
		itemBase.put("PLANK", "deco");
		itemBase.put("REED", "deco");
		itemBase.put("ROAD", "deco");
		itemBase.put("SPROUT", "deco");
		itemBase.put("TILE", "deco");
		itemBase.put("TRACK", "deco");
		itemBase.put("VINE", "deco");
		itemBase.put("BABA", "friends");
		itemBase.put("BADBAD", "friends");
		itemBase.put("BAT", "friends");
		itemBase.put("BEE", "friends");
		itemBase.put("BIRD", "friends");
		itemBase.put("BUG", "friends");
		itemBase.put("BUNNY", "friends");
		itemBase.put("CAT", "friends");
		itemBase.put("CRAB", "friends");
		itemBase.put("DOG", "friends");
		itemBase.put("FISH", "friends");
		itemBase.put("FOFO", "friends");
		itemBase.put("FROG", "friends");
		itemBase.put("GHOST", "friends");
		itemBase.put("IT", "friends");
		itemBase.put("JELLY", "friends");
		itemBase.put("JIJI", "friends");
		itemBase.put("KEKE", "friends");
		itemBase.put("LIZARD", "friends");
		itemBase.put("ME", "friends");
		itemBase.put("MONSTER", "friends");
		itemBase.put("ROBOT", "friends");
		itemBase.put("SNAIL", "friends");
		itemBase.put("SKULL", "friends");
		itemBase.put("TEETH", "friends");
		itemBase.put("TURTLE", "friends");
		itemBase.put("WORM", "friends");
		itemBase.put("BUBBLE", "intermittents");
		itemBase.put("DUST", "intermittents");
		itemBase.put("BOOK", "Inventory");
		itemBase.put("BOLT", "Inventory");
		itemBase.put("BOX", "Inventory");
		itemBase.put("CASH", "Inventory");
		itemBase.put("CLOCK", "Inventory");
		itemBase.put("COG", "Inventory");
		itemBase.put("CRYSTAL", "Inventory");
		itemBase.put("CUP", "Inventory");
		itemBase.put("DRUM", "Inventory");
		itemBase.put("FLAG", "Inventory");
		itemBase.put("GEM", "Inventory");
		itemBase.put("GUITAR", "Inventory");
		itemBase.put("HIHAT", "Inventory");
		itemBase.put("KEY", "Inventory");
		itemBase.put("LAMP", "Inventory");
		itemBase.put("LEAF", "Inventory");
		itemBase.put("MIRROR", "Inventory");
		itemBase.put("MOON", "Inventory");
		itemBase.put("ORB", "Inventory");
		itemBase.put("PANTS", "Inventory");
		itemBase.put("PAPER", "Inventory");
		itemBase.put("PLANET", "Inventory");
		itemBase.put("RING", "Inventory");
		itemBase.put("ROSE", "Inventory");
		itemBase.put("SAX", "Inventory");
		itemBase.put("SCISSORS", "Inventory");
		itemBase.put("SEED", "Inventory");
		itemBase.put("SHIRT", "Inventory");
		itemBase.put("SHOVEL", "Inventory");
		itemBase.put("STAR", "Inventory");
		itemBase.put("STICK", "Inventory");
		itemBase.put("SUN", "Inventory");
		itemBase.put("SWORD", "Inventory");
		itemBase.put("TRUMPET", "Inventory");
		itemBase.put("VASE", "Inventory");
		itemBase.put("DOOR", "KeyHub");
		itemBase.put("GATE", "KeyHub");
		itemBase.put("HOUSE", "KeyHub");
		itemBase.put("TOWER", "KeyHub");
		itemBase.put("BANANA", "nutritifs");
		itemBase.put("BOBA", "nutritifs");
		itemBase.put("BOTTLE", "nutritifs");
		itemBase.put("BURGER", "nutritifs");
		itemBase.put("CAKE", "nutritifs");
		itemBase.put("CHEESE", "nutritifs");
		itemBase.put("DONUT", "nutritifs");
		itemBase.put("DRINK", "nutritifs");
		itemBase.put("EGG", "nutritifs");
		itemBase.put("FRUIT", "nutritifs");
		itemBase.put("FUNGUS", "nutritifs");
		itemBase.put("FUNGI", "nutritifs");
		itemBase.put("LOVE", "nutritifs");
		itemBase.put("PIZZA", "nutritifs");
		itemBase.put("POTATO", "nutritifs");
		itemBase.put("PUMPKIN", "nutritifs");
		itemBase.put("TURNIP", "nutritifs");
		itemBase.put("BED", "obstacles");
		itemBase.put("BOG", "obstacles");
		itemBase.put("BOMB", "obstacles");
		itemBase.put("BRICK", "obstacles");
		itemBase.put("CHAIR", "obstacles");
		itemBase.put("CLIFF", "obstacles");
		itemBase.put("DOOR", "obstacles");
		itemBase.put("FENCE", "obstacles");
		itemBase.put("FORT", "obstacles");
		itemBase.put("GATE", "obstacles");
		itemBase.put("HEDGE", "obstacles");
		itemBase.put("HOUSE", "obstacles");
		itemBase.put("HUSK", "obstacles");
		itemBase.put("HUSKS", "obstacles");
		itemBase.put("LOCK", "obstacles");
		itemBase.put("MONITOR", "obstacles");
		itemBase.put("PIANO", "obstacles");
		itemBase.put("PILLAR", "obstacles");
		itemBase.put("PIPE", "obstacles");
		itemBase.put("ROCK", "obstacles");
		itemBase.put("RUBBLE", "obstacles");
		itemBase.put("SHELL", "obstacles");
		itemBase.put("SIGN", "obstacles");
		itemBase.put("SPIKE", "obstacles");
		itemBase.put("STATUE", "obstacles");
		itemBase.put("STUMP", "obstacles");
		itemBase.put("TABLE", "obstacles");
		itemBase.put("TOWER", "obstacles");
		itemBase.put("TREE", "obstacles");
		itemBase.put("TREES", "obstacles");
		itemBase.put("WALL", "obstacles");
		itemBase.put("BOOK", "SpecialElement");
		itemBase.put("PAPER", "SpecialElement");
		itemBase.put("BOX", "SpecialElement");
		itemBase.put("BUCKET", "SpecialElement");
		itemBase.put("FIRE", "SpecialElement");
		itemBase.put("GHOST", "SpecialElement");
		itemBase.put("KEY", "SpecialElement");
		itemBase.put("LEVER", "SpecialElement");
		itemBase.put("MIRROR", "SpecialElement");
		itemBase.put("SEED", "SpecialElement");
		itemBase.put("SPROUT", "SpecialElement");
		itemBase.put("WIND", "SpecialElement");
		itemBase.put("PLANE CLOUD", "transports");
		itemBase.put("ROCKET CLOUD", "transports");
		itemBase.put("UFO CLOUD", "transports");
		itemBase.put("CAR ROAD", "transports");
		itemBase.put("TRAIN TRACK", "transports");
		itemBase.put("CART TRACK", "transports");
		itemBase.put("STICK", "weapon");
		itemBase.put("SHOVEL", "weapon");
		itemBase.put("SWORD", "weapon");
		itemBase.put("BOLT", "weapon");

	}

	public static Map<String, String> getItemBase() {
		return Collections.unmodifiableMap(itemBase);
	}

	private ItemDatabase() {
	}
}
