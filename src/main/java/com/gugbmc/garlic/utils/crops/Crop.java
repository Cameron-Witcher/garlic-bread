package com.gugbmc.garlic.utils.crops;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import com.gugbmc.garlic.utils.CustomItem;

public class Crop {

	CustomItem ci;
	ArmorStand stand;
	Location loc;

	public Crop(Location loc, CustomItem ci) {
		this.loc = loc;
		this.ci = ci;
		stand = loc.getWorld().spawn(loc.clone().add(0.5, -100, 0.5), ArmorStand.class);
		stand.setInvisible(true);
		stand.setGravity(false);
		stand.setCustomName(ci.getName());
		stand.setCustomNameVisible(true);
		stand.setSmall(true);
		stand.teleport(stand.getLocation().clone().add(0, 99.1, 0));
	}

	public CustomItem getCustomItem() {
		return ci;
	}

	public void kill() {
		stand.remove();
	}

}
