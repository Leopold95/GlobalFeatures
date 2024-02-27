package me.leopold95.funutils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class ChunkGeneratedEvent implements Listener {
	//ChunkPopulateEvent - вызывается при заверешении генерации чанка
	// => работаем с уже ранее сгенерированным чанком
	@EventHandler
	private void onChunkGenerated(ChunkPopulateEvent event){

	}
}
