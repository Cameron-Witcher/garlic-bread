package com.gugbmc.garlic.utils.crops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gugbmc.garlic.utils.CustomItem;

public class Crop {

	CustomItem ci;
	ArmorStand stand;
	Location loc;

	public Crop(Location loc, CustomItem ci) {
		this.loc = loc;
		loc.getBlock().setType(Material.TRIPWIRE);
		this.ci = ci;
		stand = loc.getWorld().spawn(loc.clone().add(0.5, -100, 0.5), ArmorStand.class);
		stand.setInvisible(true);
		stand.setGravity(false);
		stand.setCustomName(ci.getName());
		stand.setCustomNameVisible(true);

		ItemStack model = new ItemStack(Material.CARVED_PUMPKIN);
		ItemMeta mm = model.getItemMeta();
		mm.setCustomModelData(ci.getModelData());
		model.setItemMeta(mm);
		stand.getEquipment().setHelmet(model);
		stand.teleport(stand.getLocation().clone().add(0, 99.1, 0));
	}

	public CustomItem getCustomItem() {
		return ci;
	}

	public void kill() {
		stand.remove();
	}

}
