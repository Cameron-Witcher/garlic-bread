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
import com.gugbmc.garlic.utils.items.CustomItem;
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
					for (int i = 1; i != 10; i++) {
						r = r + ItemIdentifier.getId(c.getItem(i) == null ? new ItemStack(Material.AIR) : c.getItem(i));
					}
					ItemStack air = new ItemStack(Material.AIR);
					String t = "" + ItemIdentifier.getId(air) + ItemIdentifier.getId(CustomItem.GARLIC.getItem())
							+ ItemIdentifier.getId(air) + ItemIdentifier.getId(air)
							+ ItemIdentifier.getId(new ItemStack(Material.BREAD)) + ItemIdentifier.getId(air)
							+ ItemIdentifier.getId(air) + ItemIdentifier.getId(air) + ItemIdentifier.getId(air);
					Bukkit.broadcastMessage("Recipe: " + r);
					Bukkit.broadcastMessage("Test for: " + t);
					if (r.equals(t)) {
						c.setItem(0, CustomItem.GARLIC_BREAD.getItem());
					}

				}
			}, 0);

		}
	}
}
