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
	private LinkedList<Vector3> blockRelatives;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		datum = new OreGenerationDatum();
		//прегенерируем список ближайших блоков, чтобы избежать ненужных тысяч аллокаций памяти
		blockRelatives = getBlockRelativeSteps();
	}

	public void tryGenerateVien(Chunk chunk){
//		if(chunk.getWorld().getEnvironment() != World.Environment.NORMAL)
//			return;

		//проверка на заблокированные миры
		if(datum.BANNED_WORLDS.contains(chunk.getWorld().getName()))
			return;

		if(datum.GOLD_ORE_ENABLED){
			//сразу проверяем удачу, будем ли генерировать жилу в єтом чанке
			if(!Utils.doWithChance(datum.GOLD_ORE_CHANCE))
				return;

			//рандомная высота для генерации жилы
			int startY = Utils.randomInt(datum.GOLD_ORE_HEIGHT_FROM, datum.GOLD_ORE_HEIGHT_TO);
			//рандомное положение жилы в чанке
			int startX = Utils.randomInt(0, 15); // 0-15 в конкретном чанке
			int startZ = Utils.randomInt(0, 15); // 0-15 в конкретном чанке

			//количество блоков для замены
			int replaceAmount = Utils.randomInt(datum.GOLD_ORE_AMOUNT_FROM, datum.GOLD_ORE_AMOUNT_TO + 1);

			spawnVien(new Vector3(startX, startY, startZ), chunk, replaceAmount, Material.GOLD_ORE);
		}

		if(datum.DIAMOND_ORE_ENABLED){
			//сразу проверяем удачу, будем ли генерировать жилу в єтом чанке
			if(!Utils.doWithChance(datum.DIAMOND_ORE_CHANCE))
				return;

			//рандомная высота для генерации жилы
			int startY = Utils.randomInt(datum.DIAMOND_ORE_HEIGHT_FROM, datum.DIAMOND_ORE_HEIGHT_TO);
			//рандомное положение жилы в чанке
			int startX = Utils.randomInt(0, 15); // 0-15 в конкретном чанке
			int startZ = Utils.randomInt(0, 15); // 0-15 в конкретном чанке

			//количество блоков для замены
			int replaceAmount = Utils.randomInt(datum.DIAMOND_ORE_AMOUNT_FROM, datum.DIAMOND_ORE_AMOUNT_TO + 1);

			spawnVien(new Vector3(startX, startY, startZ), chunk, replaceAmount, Material.DIAMOND_ORE);
		}
	}

	private void spawnVien(Vector3 startPos, Chunk chunk, int blocksIntoVien, Material material){
		Block startBlock = chunk.getBlock(startPos.x, startPos.y, startPos.z);

		int replaced = 0;

		//спавн жилы с ресурсом
		for (Vector3 relative: blockRelatives){
			if(replaced == blocksIntoVien)
				break;

			if(startBlock.getRelative(relative.x, relative.y, relative.z).getType() != replaceMaterial)
				continue;

			startBlock.getRelative(relative.x, relative.y, relative.z).setType(material);
			replaced++;
		}

		//System.out.println(startPos);
	}

	/**
	 * Список ближайших к блоку сторон, на котрых будут создаваться руды
	 */
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
