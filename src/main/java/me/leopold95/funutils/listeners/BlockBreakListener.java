package me.leopold95.funutils.listeners;

import me.leopold95.funutils.utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakListener implements Listener {
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event){
		//фикс драпа с гравия
		if(event.getBlock().getType() == Material.GRAVEL){
			if(event.getPlayer() == null)
				return;

			//прок шанса 40% -- ДА / НЕТ
			if (!Utils.doWithChance(40))
				return;

			event.setDropItems(false);

			//дроп флинта при успешном шансе
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT));
		}

		//фикс драпа яблок
		if(event.getBlock().getType() == Material.OAK_LEAVES || event.getBlock().getType() == Material.BIRCH_LEAVES){
			if(event.getPlayer() == null)
				return;

			//предмет в руках НЕ ножницы
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.SHEARS)
				return;

			//прок шанса 20% -- ДА / НЕТ
			if (!Utils.doWithChance(20))
				return;

			event.setDropItems(false);

			//дроп яблок при успешном шансе
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE));
		}
	}
}
