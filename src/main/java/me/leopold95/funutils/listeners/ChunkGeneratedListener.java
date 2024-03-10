package me.leopold95.funutils.listeners;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.core.oregeneration.CustomChunkGenerator;
import me.leopold95.funutils.core.oregeneration.OreCustomGenPopulator;
import me.leopold95.funutils.core.oregeneration.OreDisablePopulator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;


public class ChunkGeneratedListener implements Listener {
	//ChunkPopulateEvent - вызывается при заверешении генерации чанка
	// => работаем с уже ранее сгенерированным чанком
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onChunkGenerated(ChunkPopulateEvent event){
		//пробуем заменить неокотрые блоки в чанке
		//FunUtils.plugin.getOresGeneration().tryReplaceBlocks(event.getChunk());

		//пробуем создать руды в только что сгенерированном чанке
		//FunUtils.plugin.getOresGeneration().tryGenerateVien(event.getChunk());
	}

	@EventHandler
	private void onWorldInit(WorldInitEvent event){
		//порядок НЕ менят. Идет сверху - ввниз

		//первый этап кастом загрузки чанка
		event.getWorld().getPopulators().add(new OreDisablePopulator());

		//второй этап кастом загрузки чанка
		event.getWorld().getPopulators().add(new OreCustomGenPopulator());
	}

//	@EventHandler
//	private void onWorldInit(WorldInitEvent event){
//		event.getWorld().getPopulators().add(new CustomChunkGenerator());
//	}
}
