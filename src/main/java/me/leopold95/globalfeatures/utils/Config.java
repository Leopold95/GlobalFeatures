package me.leopold95.globalfeatures.utils;


import me.leopold95.globalfeatures.GlobalFeatures;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {
	//base cfg
	private static File configFile = null;
	private static FileConfiguration config = null;

	public static List<?> getList(String path) {
		return config.getList(path);
	}
	public static List<String> getStringList(String path) {
		return config.getStringList(path);
	}

	public static String getString(String path) {
		return ChatColor.translateAlternateColorCodes('§', config.getString(path));
	}

	public static Component getComponent(String path) {
		return Component.text(ChatColor.translateAlternateColorCodes('§', config.getString(path)));
	}

	public static ConfigurationSection getSection(String path){
		return config.getConfigurationSection(path);
	}


	public static int getInt(String path) {
		return config.getInt(path);
	}
	public static long getLong(String path) {return config.getLong(path); }

	public static boolean getBoolean(String path) {
		return config.getBoolean(path);
	}

	public static double getDouble(String path) {
		return config.getDouble(path);
	}

	//public static String getDesign(String path){return design.getString(path); }

//	public String getMessage(String path) {
//		return ChatColor.translateAlternateColorCodes('&', messagesConfig.getString(path));
//	}

	public static void register() {
		createConfig("config.yml");
	}

	private static void createConfig(String file) {
		configFile = new File(GlobalFeatures.plugin.getDataFolder(), file);
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			GlobalFeatures.plugin.saveResource(file, false);
		}
		config = new YamlConfiguration();
		try {
			config.load(configFile);
			config.save(configFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}