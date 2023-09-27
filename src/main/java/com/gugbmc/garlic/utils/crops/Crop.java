package com.gugbmc.garlic.utils.crops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gugbmc.garlic.utils.CustomItem;

public class Crop {

	CustomItem ci;
	ArmorStand stand;
	Location loc;
	int age = 0;

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
		stand.teleport(stand.getLocation().clone().add(0, 97.5, 0));
	}

	public CustomItem getCustomItem() {
		return ci;
	}

	public void kill() {
		stand.remove();
	}

	public void ageUp() {
		ageUp(1);
	}

	public boolean ageUp(int amount) {
		if (age < getMaxAge()) {
			age = age + amount;
			if (age > getMaxAge()) {
				amount = amount - (age - getMaxAge());
				age = getMaxAge();
			}

			stand.teleport(stand.getLocation().clone().add(0, amount * 0.325, 0));
			loc.getWorld().spawnParticle(Particle.COMPOSTER, getLocation().add(0.5, 0.5, 0.5), 10, 0.1, 0.1, 0.1, 0);
			return true;
		}
		return false;
	}

	public int getAge() {
		return age;
	}

	public int getMaxAge() {
		return 4;
	}

	public Location getLocation() {
		return loc.clone();
	}

}
