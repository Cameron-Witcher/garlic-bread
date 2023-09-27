package com.gugbmc.garlic.utils.items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.gugbmc.garlic.utils.CustomItem;

public class ItemIdentifier {

	private static Map<ItemStack, Character> leg = new HashMap<>();

	public static void init() {
		int i = 0;
		for (Material mat : Material.values()) {
			leg.put(new ItemStack(mat), (char) i);
			i++;
		}
		for (CustomItem it : CustomItem.values()) {
			leg.put(it.getItem(), ((char) i));
			i++;
		}
	}

	public static char getId(ItemStack i) {
		Bukkit.broadcastMessage(i.getItemMeta().getDisplayName());
		return leg.containsKey(i) ? leg.get(i) : null;
	}

}
