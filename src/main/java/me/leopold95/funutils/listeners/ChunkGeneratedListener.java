package me.leopold95.funutils.listeners;

import me.leopold95.funutils.FunUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class ChunkGeneratedListener implements Listener {
	//ChunkPopulateEvent - вызывается при заверешении генерации чанка
	// => работаем с уже ранее сгенерированным чанком
	@EventHandler
	private void onChunkGenerated(ChunkPopulateEvent event){
		FunUtils.plugin.getGeneration().tryGenerateViens(event.getChunk());
	}
}
