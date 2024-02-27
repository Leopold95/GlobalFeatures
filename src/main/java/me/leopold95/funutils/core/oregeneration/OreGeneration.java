package me.leopold95.funutils.core.oregeneration;

import me.leopold95.funutils.FunUtils;

/**
 * Класс-ядро для работы с генерацией чанков
 */
public class OreGeneration {
	private FunUtils plugin;

	//ores variables
	private OreGenerationDatum datum;

	public  OreGeneration(FunUtils plugin) {
		this.plugin = plugin;
		initDatum();
	}

	public void generateVien(){

	}

	public void generateBlock(){

	}

	/**
	 * Заполняет переменне из конфига.
	 * Вынеено в отделый класс чтобы не загрязнять этот.
	 * Отдельная генерация, чтобы дергать конфиг только один раз и при запуска сервера.
	 */
	private void initDatum(){
		datum = new OreGenerationDatum();
	}
}
