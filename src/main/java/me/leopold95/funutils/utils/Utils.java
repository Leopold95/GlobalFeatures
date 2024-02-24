package me.leopold95.funutils.utils;


import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Utils {
	public static boolean doWithChance(int chance) {
		int randomInt = (int) Math.floor(Math.random() * 100) + 1;
		if(chance > randomInt) {
			return true;
		}
		return false;
	}

	public static net.minecraft.server.v1_16_R3.ItemStack cast(ItemStack item) {
		return CraftItemStack.asNMSCopy(item);
	}


	public static ItemStack setTag(ItemStack item, NBTTagCompound tag) {
		net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = cast(item);
		nmsItemStack.setTag(tag);
		return CraftItemStack.asBukkitCopy(nmsItemStack);
	}
}
