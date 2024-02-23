package me.leopold95.funutils.listeners;

import me.leopold95.funutils.Utils;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class BlockBreakListener implements Listener {
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event){
		if(event.getBlock().getType() != Material.GRAVEL)
			return;

		if(event.getPlayer() == null)
			return;

		//список лопат
		List<Material> shovels = new ArrayList<>();
		shovels.add(Material.WOODEN_SHOVEL);
		shovels.add(Material.STONE_SHOVEL);
		shovels.add(Material.IRON_SHOVEL);
		shovels.add(Material.GOLDEN_SHOVEL);
		shovels.add(Material.DIAMOND_SHOVEL);
		shovels.add(Material.NETHERITE_SHOVEL);

		Material playerMainHandItem = event.getPlayer().getPlayer().getInventory().getItemInMainHand().getType();

		System.out.println(playerMainHandItem);

		//блок сломан лопатой/рукой
		if(shovels.contains(playerMainHandItem) || playerMainHandItem == Material.AIR){
			//выключаем стандартный драп
			event.setDropItems(false);

			System.out.println("2");

			//прок шанса 40% -- ДА / НЕТ
			if (!Utils.doWithChance(40))
				return;


			//дроп флинта при успешном шансе
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT));
		}
	}
}
