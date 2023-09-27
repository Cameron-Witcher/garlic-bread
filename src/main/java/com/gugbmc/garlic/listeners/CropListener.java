package com.gugbmc.garlic.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
	public void onArmorStandHit(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof ArmorStand && e.getDamager() instanceof Player) {
			Bukkit.broadcastMessage("1");
			if (e.getEntity().hasMetadata("crop")) {
				Bukkit.broadcastMessage("2");
				Crop crop = (Crop) e.getEntity().getMetadata("crop").get(0).value();
				crop.harvest();
			}
		}
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
					if (crop.ageUp(new Random().nextInt(1) + 1)) {
						if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
							hand.setAmount(hand.getAmount() - 1);
					}
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
			crop.harvest();

		}
	}

}
