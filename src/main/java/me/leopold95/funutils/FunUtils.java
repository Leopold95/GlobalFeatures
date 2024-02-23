package me.leopold95.funutils;

import me.leopold95.funutils.listeners.BlockBreakListener;
import me.leopold95.funutils.listeners.BlockPlaceListener;
import me.leopold95.funutils.listeners.EntityDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        Crafts.addShapedCrafts(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
