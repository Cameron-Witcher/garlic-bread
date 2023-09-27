package com.gugbmc.garlic.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.CustomItem;

public class CraftingListener implements Listener {

	Plugin plugin;

	public CraftingListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCraftingTableUpdate(InventoryClickEvent e) {
		if (e.getClickedInventory().getType().equals(InventoryType.CRAFTING)) {
			CraftingInventory c = (CraftingInventory) e.getClickedInventory();
			Map<Character, CustomItem> leg = new HashMap<>();
			String r = "";
			for (int i = 0; i != 8; i++) {
				if (c.getItem(i) == null || c.getItem(i).getType().equals(Material.AIR)) {
					r = r + 'X';
					continue;
				}
				for (CustomItem it : CustomItem.values()) {
					if (it.getItem().isSimilar(c.getItem(i))) {
						if (leg.containsValue(it)) {
							for (Entry<Character, CustomItem> en : leg.entrySet()) {
								if (en.getValue().equals(it)) {
									r = r + en.getKey();
									continue;
								}
							}
						} else {
							leg.put((char) i, it);
							r = r + ((char) i);
							continue;
						}
					}
				}
			}
			Bukkit.broadcastMessage(r);

		}
	}
}
