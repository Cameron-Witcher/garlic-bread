package com.gugbmc.garlic.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.Utils;
import com.gugbmc.garlic.utils.items.CustomItem;
import com.gugbmc.garlic.utils.items.ItemIdentifier;
import com.gugbmc.garlic.utils.recipes.Recipe;
import com.gugbmc.garlic.utils.recipes.RecipeUtils;

public class CraftingListener implements Listener {

	Plugin plugin;

	public CraftingListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCraftingTableUpdate(InventoryClickEvent e) {
		if (e.getClickedInventory() == null)
			return;
		if (e.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
			AnvilInventory a = (AnvilInventory) e.getClickedInventory();
			Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), new Runnable() {
				@Override
				public void run() {
					if(a.getItem(1).equals(new ItemStack(Material.FURNACE)) && a.getItem(2).equals(new ItemStack(Material.IRON_INGOT))) {
						a.setItem(0, CustomItem.STOVE.getItem());
					}
				}
			}, 0);

		}
		if (e.getClickedInventory().getType().equals(InventoryType.WORKBENCH)) {
			CraftingInventory c = (CraftingInventory) e.getClickedInventory();
			Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), new Runnable() {
				@Override
				public void run() {
					String r = "";
					for (int i = 1; i != 10; i++) {
						r = r + ItemIdentifier.getId(c.getItem(i) == null ? new ItemStack(Material.AIR) : c.getItem(i));
					}
					for (Recipe rec : RecipeUtils.getRecipes().values()) {
						if (rec.getRecipe().equals(r)) {
							c.setItem(0, rec.getResult());
							continue;
						}
					}

				}
			}, 0);

		}
	}
}
