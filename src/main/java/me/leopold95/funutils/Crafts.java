package me.leopold95.funutils;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class Crafts {
	public static void addShapedCrafts(FunUtils plugin){
		addDesorientationCraft(plugin);
		addChristmastrapCraft(plugin);;
		addSheerdustCraft(plugin);
		addArrowCraft(plugin);
		addExpBottleCraft(plugin);
		addBlazerodCraft(plugin);
	}


	//Дизориентация
	private static void addDesorientationCraft(FunUtils plugin){
		//desorientation-custom-recipe
		ItemStack itemDesorientation = new ItemStack(Material.ENDER_EYE);
		ItemMeta meta = itemDesorientation.getItemMeta();

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("desorientation", true);
		tag.setString("don-item", "desorientation");
		itemDesorientation = Utils.setTag(itemDesorientation, tag);

		meta.setDisplayName("§3[★] Дезориентация");
		itemDesorientation.setItemMeta(meta);

		ShapedRecipe recipeDesorientation = new ShapedRecipe(new NamespacedKey(plugin, "desorientation-custom-recipe"), itemDesorientation);
		recipeDesorientation.shape(
				" A ",
				"ABA",
				" A ");
		recipeDesorientation.setIngredient('A', Material.LAPIS_LAZULI);
		recipeDesorientation.setIngredient('B', Material.DIAMOND);
		plugin.getServer().addRecipe(recipeDesorientation);
	}

	//Трапка
	private static void addChristmastrapCraft(FunUtils plugin){
		//christmastrap-custom-recipe
		ItemStack itemEnderEye = new ItemStack(Material.NETHERITE_SCRAP);
		ItemMeta meta = itemEnderEye.getItemMeta();

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hristmastrap", true);
		tag.setString("don-item", "hristmastrap");
		itemEnderEye = Utils.setTag(itemEnderEye, tag);


		meta.setDisplayName("§c§l[★] Трапка");
		itemEnderEye.setItemMeta(meta);
		ShapedRecipe recipeEnderEye = new ShapedRecipe(new NamespacedKey(plugin, "christmastrap-custom-recipe"), itemEnderEye);
		recipeEnderEye.shape(
				" A ",
				"ABA",
				" A ");
		recipeEnderEye.setIngredient('A', Material.LAPIS_LAZULI);
		recipeEnderEye.setIngredient('B', Material.NETHERITE_SCRAP);
		plugin.getServer().addRecipe(recipeEnderEye);
	}

	//Явная пыль
	private static void addSheerdustCraft(FunUtils plugin){
		//sheerdust-custom-recipe
		ItemStack itemSheerdust = new ItemStack(Material.SUGAR);
		ItemMeta meta = itemSheerdust.getItemMeta();

		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("sheerdust", true);
		tag.setString("don-item", "sheerdust");
		itemSheerdust = Utils.setTag(itemSheerdust, tag);

		meta.setDisplayName("§b[★] Явная пыль");
		itemSheerdust.setItemMeta(meta);
		ShapedRecipe recipeSheerdust = new ShapedRecipe(new NamespacedKey(plugin, "sheerdust-custom-recipe"), itemSheerdust);
		recipeSheerdust.shape(
				" A ",
				"ABA",
				" A ");
		recipeSheerdust.setIngredient('A', Material.LAPIS_LAZULI);
		recipeSheerdust.setIngredient('B', Material.SUGAR);
		plugin.getServer().addRecipe(recipeSheerdust);
	}


	private static void addArrowCraft(FunUtils plugin){
		//arrow-custom-recipe
		ShapedRecipe recipeArrow = new ShapedRecipe(new NamespacedKey(plugin, "arrow-custom-recipe"),
				new ItemStack(Material.ARROW, 6));
		recipeArrow.shape(
				" A ",
				" B ",
				" С ");
		recipeArrow.setIngredient('A', Material.FLINT);
		recipeArrow.setIngredient('B', Material.STICK);
		recipeArrow.setIngredient('С', Material.FEATHER);
		plugin.getServer().addRecipe(recipeArrow);
	}

	private static void addExpBottleCraft(FunUtils plugin){
		ShapedRecipe recipeExpBottle = new ShapedRecipe(new NamespacedKey(plugin, "exp-bottle-custom-recipe"),
				new ItemStack(Material.EXPERIENCE_BOTTLE, 3));
		recipeExpBottle.shape(
				" A ",
				"ABA",
				" A ");
		recipeExpBottle.setIngredient('A', Material.GOLD_NUGGET);
		recipeExpBottle.setIngredient('B', Material.DIAMOND);
		plugin.getServer().addRecipe(recipeExpBottle);
	}

	private static void addBlazerodCraft(FunUtils plugin){
		ShapedRecipe recipeBlazeRod = new ShapedRecipe(new NamespacedKey(plugin, "blaze-rod-custom-recipe"),
				new ItemStack(Material.BLAZE_ROD));
		recipeBlazeRod.shape(
				"   ",
				" D ",
				" D ");

		recipeBlazeRod.setIngredient('D', Material.GOLD_INGOT);
		plugin.getServer().addRecipe(recipeBlazeRod);
	}
}
