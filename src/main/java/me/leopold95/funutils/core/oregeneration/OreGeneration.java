package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.*;

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
	private List<Material> canBeReplaced;
	private LinkedList<Vector3> blockRelatives;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		datum = new OreGenerationDatum();
		canBeReplaced = Arrays.asList(Material.STONE, Material.AIR, Material.WATER);
		blockRelatives = getBlockRelativeSteps();
	}

	public void tryGenerateViens(Chunk chunk){
		//TODO сделать генерацию тольоко для верхнего мира

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


			for (Vector3 relative: blockRelatives){
				if(replaced == replaceAmount)
					break;

				startBlock.getRelative(relative.x, relative.y, relative.z).setType(replaceMaterial);
				replaced++;
			}


			// Loop through nearby blocks
//			for (int x = -radius; x <= radius; x++) {
//				for (int y = -radius; y <= radius; y++) {
//					for (int z = -radius; z <= radius; z++) {
//
//						if (replaced == replaceAmount){
//							break;
//						}
//
//						Block targetBlock = clickLocation.clone().add(x, y, z).getBlock();
//						targetBlock.setType(replaceMaterial);
//
//						replaced++;
//					}
//				}
//			}
		}

		if(datum.DIAMOND_ORE_ENABLED){

		}
	}

	private void spawnVien(Vector3 startBlock, Chunk chunk, int blocksIntoVien, Material material){

	}

	private LinkedList<Vector3> getBlockRelativeSteps(){
		blockRelatives = new LinkedList<>();

		//изначлаьная точка
		blockRelatives.add(new Vector3(0, 0, 0));

		//список ближайших к блоку границ по горизонтали (4 макс)
		blockRelatives.add(new Vector3(1, 0, 0));
		blockRelatives.add(new Vector3(-1, 0, 0));
		blockRelatives.add(new Vector3(0, 0, 1));
		blockRelatives.add(new Vector3(0, 0, -1));

		//список ближайших к блоку границ по вертикали (2 макс)
		blockRelatives.add(new Vector3(0, 1, 0));
		blockRelatives.add(new Vector3(0, -1, 0));

		//список ближайших к блоку границ по углам (8 макс, 4 вверху блока 4 низу блока)
		blockRelatives.add(new Vector3(1, 1, 1));
		blockRelatives.add(new Vector3(1, 1, -1));
		blockRelatives.add(new Vector3(-1, 1, 1));
		blockRelatives.add(new Vector3(-1, 1, -1));

		blockRelatives.add(new Vector3(1, -1, 1));
		blockRelatives.add(new Vector3(1, -1, -1));
		blockRelatives.add(new Vector3(-1, -1, 1));
		blockRelatives.add(new Vector3(-1, -1, -1));

		return blockRelatives;
	}

	public void generateBlock(){

	}
}
