package com.gugbmc.garlic.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.CustomItem;
import com.gugbmc.garlic.utils.crops.Crop;
import com.gugbmc.garlic.utils.crops.Crops;

public class CropListener implements Listener {

	Plugin plugin;

	public CropListener(Plugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCropPlant(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
			if (hand == null)
				return;
			if (hand.getType().equals(Material.BONE_MEAL)) {
				if (Crops.isCrop(e.getClickedBlock().getLocation())) {
					Crop crop = Crops.getCrop(e.getClickedBlock().getLocation());
					crop.ageUp(new Random().nextInt(1) + 1);
					e.getClickedBlock().getWorld().spawnParticle(Particle.COMPOSTER,
							crop.getLocation().add(0.5, 0.5, 0.5), 10, 1, 1, 1, 0);
				}
				return;
			}

			for (CustomItem ci : CustomItem.values()) {
				if (!ci.getItem().isSimilar(hand))
					continue;
				Crops.addCrop(e.getClickedBlock().getLocation().clone().add(0, 1, 0), ci);
			}
		}
	}

	@EventHandler
	public void onCropHarvest(BlockBreakEvent e) {
		if (Crops.isCrop(e.getBlock().getLocation())) {
			e.setCancelled(true);
			Crop crop = Crops.getCrop(e.getBlock().getLocation());

			e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), crop.getCustomItem()
					.getItem((crop.getAge() >= crop.getMaxAge() ? new Random().nextInt(2) + 2 : 1)));

			e.getBlock().setType(Material.AIR);
			Crops.removeCrop(e.getBlock().getLocation());
		}
	}

}
