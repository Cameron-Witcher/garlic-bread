package com.gugbmc.garlic.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.Utils;
import com.gugbmc.garlic.utils.items.ItemIdentifier;

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
			Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), new Runnable() {
				@Override
				public void run() {
					String r = "";
					for (int i = 0; i != 9; i++) {
						r = r + ItemIdentifier.getId(c.getItem(i) == null ? new ItemStack(Material.AIR) : c.getItem(i));
					}
					Bukkit.broadcastMessage(r);
				}
			}, 0);

		}
	}
}
