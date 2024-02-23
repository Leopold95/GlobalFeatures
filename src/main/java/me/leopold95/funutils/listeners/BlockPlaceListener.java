package me.leopold95.funutils.listeners;

import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {
	@EventHandler
	private void onBlockPlace(BlockPlaceEvent event){
		if (event.getBlockPlaced().getType() != Material.BREWING_STAND)
			return;

		int netherWartCount = 1;

		Container stand = (Container) event.getBlock().getState();
		stand.getInventory().setItem(3, new ItemStack(Material.NETHER_WART, netherWartCount));
	}
}
