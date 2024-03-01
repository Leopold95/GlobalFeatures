package me.leopold95.funutils.listeners;

import me.leopold95.funutils.FunUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class ChunkGeneratedListener implements Listener {
	//ChunkPopulateEvent - вызывается при заверешении генерации чанка
	// => работаем с уже ранее сгенерированным чанком
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onChunkGenerated(ChunkPopulateEvent event){
		//пробуем создать руды в только что сгенерированном чанке
		FunUtils.plugin.getOresGeneration().tryGenerateVien(event.getChunk());
	}
}
