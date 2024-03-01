package me.leopold95.funutils.core.oregeneration;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CustomChunkGenerator extends org.bukkit.generator.ChunkGenerator {
	@NotNull
	@Override
	public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull org.bukkit.generator.ChunkGenerator.BiomeGrid biome) {
		ChunkData data = super.generateChunkData(world, random, x, z, biome);




		return data;
	}
}
