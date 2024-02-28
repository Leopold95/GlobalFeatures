package me.leopold95.funutils;

import me.leopold95.funutils.core.Crafts;
import me.leopold95.funutils.core.oregeneration.OreGeneration;
import me.leopold95.funutils.listeners.BlockBreakListener;
import me.leopold95.funutils.listeners.BlockPlaceListener;
import me.leopold95.funutils.listeners.ChunkGeneratedListener;
import me.leopold95.funutils.listeners.EntityDeathListener;
import me.leopold95.funutils.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunUtils extends JavaPlugin {
    public static FunUtils plugin;
    private static Crafts crafts; //менеджер крафтов
    private static OreGeneration generation;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new ChunkGeneratedListener(), this);

        Config.register();

        crafts = new Crafts(this);
        crafts.initShapedRecipes();

        generation = new OreGeneration(this);
    }

    public OreGeneration getGeneration(){
        return generation;
    }

    @Override
    public void onDisable() {
        plugin = null;
    }
}
