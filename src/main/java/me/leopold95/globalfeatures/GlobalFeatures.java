package me.leopold95.globalfeatures;

import me.leopold95.globalfeatures.core.Crafts;
import me.leopold95.globalfeatures.core.oregeneration.OreGeneration;
import me.leopold95.globalfeatures.listeners.BlockBreakListener;
import me.leopold95.globalfeatures.listeners.BlockPlaceListener;
import me.leopold95.globalfeatures.listeners.ChunkGeneratedListener;
import me.leopold95.globalfeatures.listeners.EntityDeathListener;
import me.leopold95.globalfeatures.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;



public final class GlobalFeatures extends JavaPlugin {
    public static GlobalFeatures plugin;
    private static Crafts crafts; //менеджер крафтов
    private static OreGeneration oresGeneration; //менеджер спавна руд

    @Override
    public void onEnable() {
        plugin = this;
        
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new ChunkGeneratedListener(), this);

        Config.register();

        crafts = new Crafts(this);
        oresGeneration = new OreGeneration(this);
    }

    public OreGeneration getOresGeneration(){
        return oresGeneration;
    }

    @Override
    public void onDisable() {
        plugin = null;
    }
}
