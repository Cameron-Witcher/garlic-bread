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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.CustomItem;
import com.gugbmc.garlic.utils.Utils;

public class CraftingListener implements Listener {

	Plugin plugin;

	public CraftingListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCraftingTableUpdate(InventoryClickEvent e) {
		Bukkit.broadcastMessage("Inv Click");
		if (e.getClickedInventory() == null)
			return;
		Bukkit.broadcastMessage("Type: " + e.getClickedInventory().getType());
		if (e.getClickedInventory().getType().equals(InventoryType.WORKBENCH)) {
			CraftingInventory c = (CraftingInventory) e.getClickedInventory();
			Map<Character, ItemStack> leg = new HashMap<>();
			Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), new Runnable() {
				@Override
				public void run() {
					String r = "";
					for (int i = 0; i != 8; i++) {
						if (c.getItem(i) == null || c.getItem(i).getType().equals(Material.AIR)) {
							r = r + 'X';
							continue;
						}
						for (CustomItem it : CustomItem.values()) {
							ItemStack itm = it.getItem();
							if (it.getItem().isSimilar(c.getItem(i))) {
								if (leg.containsValue(itm)) {
									for (Entry<Character, ItemStack> en : leg.entrySet()) {
										if (en.getValue().equals(itm)) {
											r = r + en.getKey();
											continue;
										}
									}
								} else {
									leg.put((char) i, itm);
									r = r + ((char) i);
									continue;
								}
							}
						}
						leg.put((char) i, c.getItem(i));
						r = r + ((char) i);
						continue;
					}
					Bukkit.broadcastMessage(r);
				}
			}, 0);

		}
	}
}
