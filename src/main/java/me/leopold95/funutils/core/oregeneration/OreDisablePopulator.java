package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class OreDisablePopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk chunk) {
        //используем "класс-ядро" так-как в нем за ранее иницилизированы знанчения
        //и не нужно из иницилизировать каждый чанк
        FunUtils.plugin.getOresGeneration().tryReplaceBlocks(chunk);
    }
}
