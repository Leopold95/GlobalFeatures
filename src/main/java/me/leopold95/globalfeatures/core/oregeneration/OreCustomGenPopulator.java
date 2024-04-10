package me.leopold95.globalfeatures.core.oregeneration;

import me.leopold95.globalfeatures.GlobalFeatures;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class OreCustomGenPopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk chunk) {
        GlobalFeatures.plugin.getOresGeneration().tryGenerateVien(chunk);
    }
}
