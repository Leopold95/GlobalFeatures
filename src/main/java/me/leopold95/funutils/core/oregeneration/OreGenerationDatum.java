package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class OreGenerationDatum {
	//Gold ores
	public final int GOLD_ORE_CHANCE;
	public final int GOLD_ORE_AMOUNT_FROM;
	public final int GOLD_ORE_AMOUNT_TO;
	public final int GOLD_ORE_HEIGHT_FROM;
	public final int GOLD_ORE_HEIGHT_TO;
	public final boolean GOLD_ORE_ENABLED;
	//Gold ores

	//Diamond ores
	public final int DIAMOND_ORE_CHANCE;
	public final int DIAMOND_ORE_AMOUNT_FROM;
	public final int DIAMOND_ORE_AMOUNT_TO;
	public final int DIAMOND_ORE_HEIGHT_FROM;
	public final int DIAMOND_ORE_HEIGHT_TO;
	public final boolean DIAMOND_ORE_ENABLED;
	//Diamond ores

	public final List<String> BANNED_WORLDS;

	public OreGenerationDatum(){
		GOLD_ORE_CHANCE = Config.getInt("ore-generation.gold_ore.chance");
		GOLD_ORE_AMOUNT_FROM = Integer.parseInt(Config.getString("ore-generation.gold_ore.amount").split("~")[0]);
		GOLD_ORE_AMOUNT_TO = Integer.parseInt(Config.getString("ore-generation.gold_ore.amount").split("~")[1]);
		GOLD_ORE_HEIGHT_FROM = Integer.parseInt(Config.getString("ore-generation.gold_ore.height").split("~")[0]);
		GOLD_ORE_HEIGHT_TO = Integer.parseInt(Config.getString("ore-generation.gold_ore.height").split("~")[1]);
		GOLD_ORE_ENABLED = Config.getBoolean("ore-generation.gold_ore.enabled");

		DIAMOND_ORE_CHANCE = Config.getInt("ore-generation.diamond_ore.chance");
		DIAMOND_ORE_AMOUNT_FROM = Integer.parseInt(Config.getString("ore-generation.diamond_ore.amount").split("~")[0]);
		DIAMOND_ORE_AMOUNT_TO = Integer.parseInt(Config.getString("ore-generation.diamond_ore.amount").split("~")[1]);
		DIAMOND_ORE_HEIGHT_FROM = Integer.parseInt(Config.getString("ore-generation.diamond_ore.height").split("~")[0]);
		DIAMOND_ORE_HEIGHT_TO = Integer.parseInt(Config.getString("ore-generation.diamond_ore.height").split("~")[1]);
		DIAMOND_ORE_ENABLED = Config.getBoolean("ore-generation.diamond_ore.enabled");

		BANNED_WORLDS = Config.getStringList("ore-generation.banned-worlds");
	}
}
