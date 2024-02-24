package me.leopold95.funutils.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityDeathListener implements Listener {
	@EventHandler
	private void onEntityDeath(EntityDeathEvent event){
		//фикс лута с курицы
		if(event.getEntity() instanceof Chicken){
			//список лута без перьев
			List<ItemStack> updatedDrops = new ArrayList<>();

			//добавить в дроп все, что не перо
			for(ItemStack item : event.getDrops()){
				if(item.getType() != Material.FEATHER)
					updatedDrops.add(item);
			}

			//очистить дроп с моба
			event.getDrops().clear();

			Random rand = new Random();
			int randomNumber = rand.nextInt(4 - 1) + 1; //от [1 до 3]

			//добавить к дропу перо [1; 3]
			updatedDrops.add(new ItemStack(Material.FEATHER, randomNumber));

			//дроп из обновленного списка
			event.getDrops().addAll(updatedDrops);
		}

		//фикс лута с эндера
		if(event.getEntity() instanceof Enderman){
			//список лута без жемчуга
			List<ItemStack> updatedDrops = new ArrayList<>();

			//добавить в дроп все, что не жемчуг
			for(ItemStack item : event.getDrops()){
				if(item.getType() != Material.ENDER_PEARL)
					updatedDrops.add(item);
			}

			//очистить дроп с моба
			event.getDrops().clear();

			Random rand = new Random();
			int randomNumber = rand.nextInt(4 - 1) + 1; //от [1 до 3]

			//добавить к дропу жемчуг [1; 3]
			updatedDrops.add(new ItemStack(Material.ENDER_PEARL, randomNumber));

			//дроп из обновленного списка
			event.getDrops().addAll(updatedDrops);;
		}
	}
}
