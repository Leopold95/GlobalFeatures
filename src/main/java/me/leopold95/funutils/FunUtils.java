package me.leopold95.funutils;

import me.leopold95.funutils.core.Crafts;
import me.leopold95.funutils.listeners.BlockBreakListener;
import me.leopold95.funutils.listeners.BlockPlaceListener;
import me.leopold95.funutils.listeners.EntityDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunUtils extends JavaPlugin {
    private Crafts crafts; //менеджер крафтов

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        crafts = new Crafts(this);
        crafts.initShapedRecipes();
    }

    @Override
    public void onDisable() {
    }
}
