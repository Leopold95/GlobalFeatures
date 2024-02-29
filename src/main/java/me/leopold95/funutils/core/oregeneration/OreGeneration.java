package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.utils.Config;
import me.leopold95.funutils.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

//Алгоритм
//в каком-то чанке выбрать первую рандомную точку с шаном
//от этой точки начать генерировать жилу(если в жиле 1 блок, сгенерировать его в точке начала)



/**
 * Класс-ядро для работы с генерацией чанков
 */
public class OreGeneration {
	private FunUtils plugin;
	private Material replaceMaterial = Material.STONE;
	private LinkedList<Vector3> blockRelatives;
	private ConfigurationSection oresConfigSection;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		//прегенерируем список ближайших блоков, чтобы избежать ненужных тысяч аллокаций памяти
		blockRelatives = getBlockRelativeSteps();
		oresConfigSection = Config.getSection("ore-generation.ores");
	}

	public void tryGenerateVien(Chunk chunk){
		//проверка на заблокированные миры
		if(Config.getStringList("ore-generation.banned-worlds").contains(chunk.getWorld().getName()))
			return;

		for (String key: oresConfigSection.getKeys(false)){
			//натсройки с текущей рудой
			ConfigurationSection currentCfg = Config.getSection("ore-generation.ores."+key);

			//если руда выключена - скип
			if(!currentCfg.getBoolean("enabled"))
				continue;

			int spawnChance = getValue(currentCfg, "chance");

			//System.out.println(chanceFrom +" "+ chanceTo);

			//сразу проверяем удачу, будем ли генерировать жилу в єтом чанке
			if(!Utils.doWithChance(spawnChance))
				return;

			//System.out.println(spawnChance);

			//рандомное положение жилы в чанке
			int startX;
			int startZ;
			int startY;

			int howManyTimesSpawnVien = getValue(currentCfg, "cycle-random");

			for (int i = 1; i <= howManyTimesSpawnVien; i++){
				startX = Utils.randomInt(0, 15); // 0-15 в конкретном чанке
				startZ = Utils.randomInt(0, 15); // 0-15 в конкретном чанке
				startY = getValue(currentCfg, "height");
				int replaceAmount = getValue(currentCfg, "amount");
				spawnVien(new Vector3(startX, startY, startZ), chunk, replaceAmount, Material.getMaterial(currentCfg.getString("material")));
			}
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

	private int getValue(ConfigurationSection section, String str){
		int from;
		int to;
		try { //ваначле пробуем рандом, еслии ошибка - значит фиксировано
			String[] heights = section.getString(str).split("~");
			from = Integer.parseInt(heights[0]);
			to = Integer.parseInt(heights[1]);

			//рандомная высота для генерации жилы
			return Utils.randomInt(from, to);
		}
		catch (Exception exp){
			return section.getInt(str);
		}
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
