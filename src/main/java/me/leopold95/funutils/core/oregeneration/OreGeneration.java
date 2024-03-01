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
	private boolean canReplaceAir;
	private List<ConfigurationSection> preLoadedCfgsList;
	private List<String> bannedWorlds;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		//прегенерируем список ближайших блоков, чтобы избежать ненужных тысяч аллокаций памяти
		blockRelatives = getBlockRelativeSteps();
		oresConfigSection = Config.getSection("ore-generation.ores");
		canReplaceAir = Config.getBoolean("ore-generation.replace-air");

		//пре инициализация парметров конфига, для увеличения произврдительности
		//путем заранее аллоцированого списка руд
		preLoadedCfgsList = new ArrayList<>();
		for (String key: oresConfigSection.getKeys(false))
			preLoadedCfgsList.add(Config.getSection("ore-generation.ores."+key));

		bannedWorlds = Config.getStringList("ore-generation.banned-worlds");
	}

	public void tryGenerateVien(Chunk chunk){
		//проверка на заблокированные миры
		if(bannedWorlds.contains(chunk.getWorld().getName()))
			return;

		//для каждой руды в каждом созданном чанке......
		for(ConfigurationSection oreCfg: preLoadedCfgsList){
			if(!oreCfg.getBoolean("enabled"))
				continue;

			//сразу проверяем, будем ли генерировать жилу в этом чанке
			if(!Utils.doWithChance(getFixedOrRandomCfgValue(oreCfg, "chance")))
				return;


			//если шансы прокнули - значит руда будет 100% сгенерирована Х раз в конкретном чанке

			//рандомное положение жилы в чанке
			int startX;
			int startZ;
			int startY;

			int howManyTimesSpawnVien = getFixedOrRandomCfgValue(oreCfg, "cycle-random");
			int replaceAmount;

			for (int i = 1; i <= howManyTimesSpawnVien; i++){
				startX = Utils.fastRandom(0, 15); // 0-15 в конкретном чанке
				startZ = Utils.fastRandom(0, 15); // 0-15 в конкретном чанке
				startY = getFixedOrRandomCfgValue(oreCfg, "height");
				replaceAmount = getFixedOrRandomCfgValue(oreCfg, "amount");
				spawnVien(new Vector3(startX, startY, startZ), chunk, replaceAmount, Material.getMaterial(oreCfg.getString("material")));
			}

		}

		//для каждой руды в каждом созданном чанке......
//		for (String key: oresConfigSection.getKeys(false)){
//			//натсройки с текущей рудой
//			ConfigurationSection currentCfg = Config.getSection("ore-generation.ores."+key);
//
//			//если руда выключена - скип
//			if(!currentCfg.getBoolean("enabled"))
//				continue;
//
//			int spawnChance = getValue(currentCfg, "chance");
//
//			//System.out.println(chanceFrom +" "+ chanceTo);
//
//			//сразу проверяем, будем ли генерировать жилу в єтом чанке
//			if(!Utils.doWithChance(spawnChance))
//				return;
//
//			//System.out.println(spawnChance);
//
//			//рандомное положение жилы в чанке
//			int startX;
//			int startZ;
//			int startY;
//
//			int howManyTimesSpawnVien = getValue(currentCfg, "cycle-random");
//			int replaceAmount;
//
//			for (int i = 1; i <= howManyTimesSpawnVien; i++){
//				startX = Utils.fastRandom(0, 15); // 0-15 в конкретном чанке
//				startZ = Utils.fastRandom(0, 15); // 0-15 в конкретном чанке
//				startY = getValue(currentCfg, "height");
//				replaceAmount = getValue(currentCfg, "amount");
//				spawnVien(new Vector3(startX, startY, startZ), chunk, replaceAmount, Material.getMaterial(currentCfg.getString("material")));
//			}
//		}
	}

	private void spawnVien(Vector3 startPos, Chunk chunk, int blocksIntoVien, Material material){
		Block startBlock = chunk.getBlock(startPos.x, startPos.y, startPos.z);

		int replaced = 0;

		//спавн жилы с ресурсом
		for (Vector3 relative: blockRelatives){
			if(replaced == blocksIntoVien)
				break;

//			if(canReplaceAir && startBlock.getRelative(relative.x, relative.y, relative.z).getType() != replaceMaterial)
//				continue;

			startBlock.getRelative(relative.x, relative.y, relative.z).setType(material);
			replaced++;
		}

		//System.out.println(startPos);
	}

//	public List<SimpleBlockInfo> getBlocksToReplace(Vector3 startPos, int blocksIntoVien, Material material){
//		List<SimpleBlockInfo> blocksToReplace = new ArrayList<>();
//
//		//Block startBlock = chunk.getBlock(startPos.x, startPos.y, startPos.z);
//
//		int replaced = 0;
//
//		//спавн жилы с ресурсом
//		for (Vector3 relative: blockRelatives){
//			if(replaced == blocksIntoVien)
//				break;
//
////			if(canReplaceAir && startBlock.getRelative(relative.x, relative.y, relative.z).getType() != replaceMaterial)
////				continue;
//
//			blocksToReplace.add(new SimpleBlockInfo(new Vector3(relative.x, relative.y, relative.z), material));
//
//			//startBlock.getRelative(relative.x, relative.y, relative.z).setType(material);
//			replaced++;
//		}
//
//
//		return blocksToReplace;
//	}

	private int getFixedOrRandomCfgValue(ConfigurationSection section, String str) {
		String value = section.getString(str);
		String[] parts = value.split("~");

		if (parts.length == 2) { //вначале проверяем на то что, формат рандомній
			int from = Integer.parseInt(parts[0]);
			int to = Integer.parseInt(parts[1]);
			return Utils.fastRandom(from, to);
		}
		return section.getInt(str);
	}

//	private int getValue(ConfigurationSection section, String str){
//		int from;
//		int to;
//		try { //ваначле пробуем рандом, еслии ошибка - значит фиксировано
//			String[] heights = section.getString(str).split("~");
//			from = Integer.parseInt(heights[0]);
//			to = Integer.parseInt(heights[1]);
//
//			//рандомная высота для генерации жилы
//			return Utils.randomInt(from, to);
//		}
//		catch (Exception e){
//			return section.getInt(str);
//		}
//	}

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
