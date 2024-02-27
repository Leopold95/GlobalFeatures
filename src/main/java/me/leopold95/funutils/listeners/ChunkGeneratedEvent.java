package me.leopold95.funutils.listeners;

import me.leopold95.funutils.FunUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkPopulateEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ChunkGeneratedEvent implements Listener {
	//ChunkPopulateEvent - вызывается при заверешении генерации чанка
	// => работаем с уже ранее сгенерированным чанком
	@EventHandler
	private void onChunkGenerated(ChunkPopulateEvent event){
		FunUtils.plugin.getGeneration().tryGenerateViens(event.getChunk());
	}
}
