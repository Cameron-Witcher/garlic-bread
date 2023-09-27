package com.gugbmc.garlic.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.Crops;
import com.gugbmc.garlic.utils.CustomItem;

public class CropListener implements Listener {

	Plugin plugin;

	public CropListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCropPlant(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& e.getPlayer().getInventory().getItemInMainHand().isSimilar(CustomItem.GARLIC.getItem())) {
			Crops.addCrop(e.getClickedBlock().getLocation().clone().add(0, 1, 0), CustomItem.GARLIC);
		}
	}

	@EventHandler
	public void onCropHarvest(BlockBreakEvent e) {
		if (Crops.isCrop(e.getBlock().getLocation())) {
			CustomItem ci = Crops.getCrop(e.getBlock().getLocation());
			e.setCancelled(true);

			// TODO Check to make sure the item being broken is fully grown...
			Ageable ageable = (Ageable) e.getBlock().getState().getBlockData();
			e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
					ci.getItem((ageable.getAge() >= ageable.getMaximumAge() ? new Random().nextInt(2) + 2 : 1)));

			e.getBlock().setType(Material.AIR);
			Crops.removeCrop(e.getBlock().getLocation());
		}
	}

}
