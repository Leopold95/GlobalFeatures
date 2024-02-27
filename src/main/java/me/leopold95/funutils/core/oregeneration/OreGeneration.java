package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Алгоритм
//в каком-то чанке выбрать первую рандомную точку с шаном
//от этой точки начать генерировать жилу(если в жиле 1 блок, сгенерировать его в точке начала)



/**
 * Класс-ядро для работы с генерацией чанков
 */
public class OreGeneration {
	private FunUtils plugin;
	private OreGenerationDatum datum;
	private Material replaceMaterial = Material.STONE;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		datum = new OreGenerationDatum();
	}

	public void tryGenerateViens(Chunk chunk){
		if(datum.GOLD_ORE_ENABLED){
			//сразу проверяем удачу, будем ли генерировать жилу в єтом чанке
			if(!Utils.doWithChance(datum.GOLD_ORE_CHANCE))
				return;

			//рандомная высота для генерации жилы
			int startY = 80;//Utils.randomInt(datum.GOLD_ORE_HEIGHT_FROM, datum.GOLD_ORE_HEIGHT_TO);
			//рандомное положение жилы в чанке
			int startX = Utils.randomInt(0, 15); // 0-15
			int startZ = Utils.randomInt(0, 15); // 0-15

			Block startBlock = chunk.getBlock(startX, startY, startZ);

			//количество блоков для замены
			int replaceAmount = Utils.randomInt(datum.GOLD_ORE_AMOUNT_FROM, datum.GOLD_ORE_AMOUNT_TO + 1);
			int replaced = 0;

			//System.out.println(startBlock + " " + replaceAmount);

			//спавн жилы с ресурсом
			Location clickLocation = startBlock.getLocation();
			int radius = 1; // Example max radius
			Material replaceMaterial = Material.GOLD_BLOCK; // Example replacement block

			// Loop through nearby blocks
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {

						if (replaced == replaceAmount)
							break;

						Block targetBlock = clickLocation.clone().add(x, y, z).getBlock();
						targetBlock.setType(replaceMaterial);

						replaced++;
					}
				}
			}
		}

		if(datum.DIAMOND_ORE_ENABLED){

		}
	}

	private void spawnVien(Location startBlock, Chunk chunk, int blocksIntoVien){

	}

	public void generateBlock(){

	}
}
