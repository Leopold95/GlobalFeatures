package me.leopold95.funutils.listeners;

import me.leopold95.funutils.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
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

		//фикс лута с зомби
		if(event.getEntity() instanceof Zombie){
			//список лута без морковки
			List<ItemStack> updatedDrops = new ArrayList<>();

			//добавить в дроп все, что не жемчуг
			for(ItemStack item : event.getDrops()){
				if(item.getType() != Material.CARROT)
					updatedDrops.add(item);
			}

			//очистить дроп с моба
			event.getDrops().clear();

			Random rand = new Random();
			int randomNumber = rand.nextInt(4 - 1) + 1; //от [1 до 3]

			//добавить к дропу морковь [1; 3]
			updatedDrops.add(new ItemStack(Material.CARROT, randomNumber));

			//дроп из обновленного списка
			event.getDrops().addAll(updatedDrops);;
		}

		//фикс лута с паука
		if(event.getEntity() instanceof Spider){
			//список лута без,,,,
			List<ItemStack> updatedDrops = new ArrayList<>();
			List<Material> blockDrops = Arrays.asList(Material.STRING, Material.SPIDER_EYE, Material.FERMENTED_SPIDER_EYE);

			//добавить в дроп все, что не....
			for(ItemStack item : event.getDrops()){
				if(!blockDrops.contains(item.getType()))
					updatedDrops.add(item);
			}

			//очистить дроп с моба
			event.getDrops().clear();

			Random rand = new Random();

			//100% 1-2 нити
			updatedDrops.add(new ItemStack(Material.STRING, rand.nextInt(2) + 1)); //от [1 до 2]

			//50% 1 паучий глаз
			if(Utils.doWithChance(50))
				updatedDrops.add(new ItemStack(Material.SPIDER_EYE, 1));

			//20% 1 маринованный паучий глаз
			if(Utils.doWithChance(20))
				updatedDrops.add(new ItemStack(Material.FERMENTED_SPIDER_EYE, 1));

			//дроп из обновленного списка
			event.getDrops().addAll(updatedDrops);;
		}
	}
}
