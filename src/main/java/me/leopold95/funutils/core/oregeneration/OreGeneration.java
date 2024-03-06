package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.utils.Config;
import me.leopold95.funutils.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

/**
 * Класс-ядро для работы с генерацией чанков
 */
public class OreGeneration {
	private FunUtils plugin;
	private Material replaceMaterial = Material.STONE;
	private LinkedList<Vector3> blockRelatives;
	private ConfigurationSection oresConfigSection;
	//ore spawn
	private boolean canReplaceAir;
	private List<ConfigurationSection> preLoadedCfgsList;
	private List<String> bannedWorlds;
	//block replace
	private boolean canReplaceBlocks;
	private List<Material> bannedBlocks;
	private int banHeightFrom;
	private int banHeightTo;
	private Material banReplaceMat;


	public OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		//-----Генерация руд-------

		//прегенерируем список ближайших блоков, чтобы избежать ненужных тысяч аллокаций памяти
		blockRelatives = getBlockRelativeSteps();
		//предгенериуем секцию с конфигами для руд
		oresConfigSection = Config.getSection("ore-generation.ores");
		//можно ли заменять воздух
		canReplaceAir = !Config.getBoolean("ore-generation.replace-air");

		//пре инициализация парметров конфига, для увеличения произврдительности
		//путем заранее аллоцированого списка руд
		preLoadedCfgsList = new ArrayList<>();
		for (String key: oresConfigSection.getKeys(false))
			preLoadedCfgsList.add(Config.getSection("ore-generation.ores."+key));

		//список заблокированных миров
		bannedWorlds = Config.getStringList("ore-generation.banned-worlds");


		//-----Замена блоков-------

		//включена ли замена блоков
		canReplaceBlocks = Config.getBoolean("ore-generation.block-replace-enabled");
		bannedBlocks = new ArrayList<>();

		//иницилизируем материалы, котрые нужно заменить
		for(String materialStr: Config.getStringList("ore-generation.banned-blocks"))
			bannedBlocks.add(Material.getMaterial(materialStr));

		//глобаная высота для замены
		String[] banHeights = Config.getString("ore-generation.global-ban-height").split("~");
		banHeightFrom = Integer.parseInt(banHeights[0]);
		banHeightTo = Integer.parseInt(banHeights[1]);

		//материал, на который нужно заменить блоки
		banReplaceMat = Material.getMaterial(Config.getString("ore-generation.replace-block"));
	}

	/**
	 * Пытается заспавнить жилы с рудами в чанке
	 */
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

			//сколько раз будет заспавнена жила на рандомных координатах
			int howManyTimesSpawnVien = getFixedOrRandomCfgValue(oreCfg, "cycle-random");
			int replaceAmount; //количество блоков руды в жиле
			int startX;
			int startZ;
			int startY;

			//материал проще иницилизировать заранее, ведь
			//1 - так мы не будем делать это каждый раз в цикло
			//2 - материал все равно всегда будет один в конкретном месте
			Material oreType = Material.getMaterial(oreCfg.getString("material"));

			for (int i = 1; i <= howManyTimesSpawnVien; i++){
				//по сколько руда може выйти за границы чанка
				//и максимум 27 блоков руды (3-3-3)
				//огганичим радиус появления руды в чанке

				//рандомное положение жилы в чанке
				startX = Utils.fastRandom(3, 12); // 0-15 в конкретном чанке
				startZ = Utils.fastRandom(3, 12); // 0-15 в конкретном чанке
				startY = getFixedOrRandomCfgValue(oreCfg, "height");
				replaceAmount = getFixedOrRandomCfgValue(oreCfg, "amount");
				spawnVien(chunk, replaceAmount, oreType, startX, startY, startZ);
			}
		}
	}

	/**
	 * По возможности заменяет указанные блоки в чанке
	 * на указанноц высоте
	 */
	public void tryReplaceBlocks(Chunk chunk){
		if(!canReplaceBlocks)
			return;

		//проверка на заблокированные миры
		if(bannedWorlds.contains(chunk.getWorld().getName()))
			return;

		//для каждого блока в чанке в пределе опредленной высоты....
		for(int x = 1; x < 15; x++){
			for (int y = banHeightFrom; y < banHeightTo; y++){
				for(int z = 1; z < 15; z++){
					Block currentBlock = chunk.getBlock(x, y, z);
					if(bannedBlocks.contains(currentBlock.getType()))
						currentBlock.setType(banReplaceMat);
				}
			}
		}
	}

	/**
	 * Спавн жилы в чанке на локальных координатах чанка
	 */
	private void spawnVien(Chunk chunk, int blocksIntoVien, Material material, int startX, int startY, int startZ){
		Block startBlock = chunk.getBlock(startX, startY, startZ);

		int replaced = 0;

		//спавн жилы с ресурсом
		for (Vector3 relative: blockRelatives){
			if(replaced == blocksIntoVien)
				break;

			Block relativeBlock = startBlock.getRelative(relative.x, relative.y, relative.z);

			if(canReplaceAir && relativeBlock.getType() != replaceMaterial)
				continue;

			relativeBlock.setType(material);
			replaced++;
		}

		//System.out.println(startPos);
	}

	/**
	 * Выдает рандомный или фиксированный int из кфгсекции и парметра
	 */
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
}
