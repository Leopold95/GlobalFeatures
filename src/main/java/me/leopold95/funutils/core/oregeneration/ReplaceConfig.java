package me.leopold95.funutils.core.oregeneration;

import org.bukkit.Material;

public final class ReplaceConfig {
    public Material fromID;
    public Material toID;
    public int fromHeight;
    public int toHeight;

    public ReplaceConfig(String fromID, String toID, int fromHeight, int toHeight){
        this.fromID = Material.getMaterial(fromID);
        this.toID = Material.getMaterial(toID);
        this.fromHeight = fromHeight;
        this.toHeight = toHeight;
    }
}
