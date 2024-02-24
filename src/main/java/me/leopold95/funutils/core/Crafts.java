package me.leopold95.funutils.core;

import me.leopold95.funutils.FunUtils;
import me.leopold95.funutils.utils.Utils;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class Crafts {
	private FunUtils plugin;

	public Crafts(FunUtils plugin){
		this.plugin = plugin;
	}


	public void initShapedRecipes(){
		plugin.getServer().addRecipe(resDisorientation()); //Дизориентация
		plugin.getServer().addRecipe(resChristmastrap()); //Трапка
		plugin.getServer().addRecipe(resSheerdust()); //Явная пыль
		plugin.getServer().addRecipe(resArrows()); //6 стрел при крафте
		plugin.getServer().addRecipe(resExpBottle()); //3 пузырька опыта
		plugin.getServer().addRecipe(resBlazeRod()); //стержень ифрита
		plugin.getServer().addRecipe(resFermentedEye()); //маринованный паучий глаз
		plugin.getServer().addRecipe(resEnchantedApple()); //зачарованное золотое яблоко
		plugin.getServer().addRecipe(resChorusFruit()); //фрукт хроуса
		plugin.getServer().addRecipe(resTotemOfUndying()); //тотем бессмертия
		plugin.getServer().addRecipe(resGlowStone()); //свето пыль
}

	//Дизориентация
	private ShapedRecipe resDisorientation(){
		//desorientation-custom-recipe
		ItemStack result = new ItemStack(Material.ENDER_EYE);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("desorientation", true);
		tag.setString("don-item", "desorientation");
		result = Utils.setTag(result, tag);

		ItemMeta meta = result.getItemMeta();
		meta.setDisplayName("§3[★] Дезориентация");
		result.setItemMeta(meta);

		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "desorientation-custom-recipe"), result);
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.LAPIS_LAZULI);
		recipe.setIngredient('B', Material.DIAMOND);

		return recipe;
	}

	//Трапка
	private ShapedRecipe resChristmastrap(){
		//christmastrap-custom-recipe
		ItemStack result = new ItemStack(Material.NETHERITE_SCRAP);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("christmastrap", true);
		tag.setString("don-item", "christmastrap");
		result = Utils.setTag(result, tag);

		ItemMeta meta = result.getItemMeta();
		meta.setDisplayName("§c§l[★] Трапка");
		result.setItemMeta(meta);

		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "christmastrap-custom-recipe"), result);
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.LAPIS_LAZULI);
		recipe.setIngredient('B', Material.NETHERITE_SCRAP);

		return recipe;
	}

	//Явная пыль
	private ShapedRecipe resSheerdust(){
		ItemStack result = new ItemStack(Material.SUGAR);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("sheerdust", true);
		tag.setString("don-item", "sheerdust");
		result = Utils.setTag(result, tag);

		ItemMeta meta = result.getItemMeta();
		meta.setDisplayName("§b[★] Явная пыль");
		result.setItemMeta(meta);

		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "sheerdust-custom-recipe"), result);
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.LAPIS_LAZULI);
		recipe.setIngredient('B', Material.SUGAR);

		return recipe;
	}

	//6 стрел в крафте
	private  ShapedRecipe resArrows(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "arrow-custom-recipe"),
				new ItemStack(Material.ARROW, 6));
		recipe.shape(
				" A ",
				" B ",
				" С ");
		recipe.setIngredient('A', Material.FLINT);
		recipe.setIngredient('B', Material.STICK);
		recipe.setIngredient('С', Material.FEATHER);

		return recipe;
	}

	//3 пузырька опыта
	private  ShapedRecipe resExpBottle(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "exp-bottle-custom-recipe"),
				new ItemStack(Material.EXPERIENCE_BOTTLE, 3));
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.GOLD_NUGGET);
		recipe.setIngredient('B', Material.DIAMOND);

		return recipe;
	}

	//стержень инфрита
	private ShapedRecipe resBlazeRod(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "blaze-rod-custom-recipe"),
				new ItemStack(Material.BLAZE_ROD));
		recipe.shape(
				"   ",
				" D ",
				" D ");

		recipe.setIngredient('D', Material.GOLD_INGOT);

		return recipe;
	}

	//Маринованный паучий глаз
	private ShapedRecipe resFermentedEye(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "fermented-eye-custom-recipe"),
				new ItemStack(Material.FERMENTED_SPIDER_EYE));
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.BLAZE_POWDER);
		recipe.setIngredient('B', Material.SPIDER_EYE);

		return recipe;
	}

	//зачарованное золотое яблоко
	private ShapedRecipe resEnchantedApple(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "ench-golden-apple-custom-recipe"),
				new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
		recipe.shape(
				"CAC",
				"ABA",
				"CAC");
		recipe.setIngredient('A', Material.NETHERITE_INGOT);
		recipe.setIngredient('B', Material.APPLE);
		recipe.setIngredient('C', Material.GOLD_INGOT);

		return recipe;
	}

	//фрукт хоуса
	private ShapedRecipe resChorusFruit(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "chorus-fruit-custom-recipe"),
				new ItemStack(Material.CHORUS_FRUIT));
		recipe.shape(
				" A ",
				"ABA",
				" A ");
		recipe.setIngredient('A', Material.LAPIS_LAZULI);
		recipe.setIngredient('B', Material.CARROT);

		return recipe;
	}

	//тотем бессмертия
	private ShapedRecipe resTotemOfUndying(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "totem-of-custom-recipe"),
				new ItemStack(Material.TOTEM_OF_UNDYING));
		recipe.shape(
				"AAA",
				"ABA",
				"AAA");
		recipe.setIngredient('A', Material.GOLD_INGOT);
		recipe.setIngredient('B', Material.GLOWSTONE_DUST);

		return recipe;
	}

	//свето пыль
	private ShapedRecipe resGlowStone(){
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "glowstone-custom-recipe"),
				new ItemStack(Material.GLOWSTONE_DUST));
		recipe.shape(
				"CAC",
				"ABA",
				"CAC");
		recipe.setIngredient('C', Material.BLAZE_POWDER);
		recipe.setIngredient('A', Material.GOLD_INGOT);
		recipe.setIngredient('B', Material.NETHERITE_INGOT);

		return recipe;
	}
}
