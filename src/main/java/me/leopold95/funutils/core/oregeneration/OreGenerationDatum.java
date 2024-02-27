package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.utils.Config;

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

	public OreGenerationDatum(){
		GOLD_ORE_CHANCE = Config.getInt("gold_ore.chance");
		GOLD_ORE_AMOUNT_FROM = Integer.parseInt(Config.getString("gold_ore.amount").split("~")[0]);
		GOLD_ORE_AMOUNT_TO = Integer.parseInt(Config.getString("gold_ore.amount").split("~")[1]);
		GOLD_ORE_HEIGHT_FROM = Integer.parseInt(Config.getString("gold_ore.height").split("~")[0]);
		GOLD_ORE_HEIGHT_TO = Integer.parseInt(Config.getString("gold_ore.height").split("~")[1]);
		GOLD_ORE_ENABLED = Config.getBoolean("gold_ore.enabled");

		DIAMOND_ORE_CHANCE = Config.getInt("diamond_ore.chance");
		DIAMOND_ORE_AMOUNT_FROM = Integer.parseInt(Config.getString("diamond_ore.amount").split("~")[0]);
		DIAMOND_ORE_AMOUNT_TO = Integer.parseInt(Config.getString("diamond_ore.amount").split("~")[1]);
		DIAMOND_ORE_HEIGHT_FROM = Integer.parseInt(Config.getString("diamond_ore.height").split("~")[0]);
		DIAMOND_ORE_HEIGHT_TO = Integer.parseInt(Config.getString("diamond_ore.height").split("~")[1]);
		DIAMOND_ORE_ENABLED = Config.getBoolean("diamond_ore.enabled");
	}
}
