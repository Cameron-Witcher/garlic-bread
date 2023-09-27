package com.gugbmc.garlic.utils.crops;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.gugbmc.garlic.utils.Utils;
import com.gugbmc.garlic.utils.items.CustomItem;

public class Crop {

	CustomItem ci;
	ArmorStand stand;
	Location loc;
	int age = 0;
	int max_age = 4;

	public Crop(Location loc, CustomItem ci) {
		this.loc = loc;
		loc.getBlock().setType(Material.TRIPWIRE);
		this.ci = ci;
		stand = loc.getWorld().spawn(loc.clone().add(0.5, -100, 0.5), ArmorStand.class);
		stand.setInvisible(true);
		stand.setGravity(false);
		ItemStack model = new ItemStack(Material.CARVED_PUMPKIN);
		ItemMeta mm = model.getItemMeta();
		mm.setCustomModelData(ci.getModelData());
		model.setItemMeta(mm);
		stand.getEquipment().setHelmet(model);
		stand.teleport(stand.getLocation().clone().add(0, 97.5, 0));
		stand.setMetadata("crop", new FixedMetadataValue(Utils.getPlugin(), this));
	}

	public void setAge(int age) {
		this.age = age;
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
		if (age < max_age) {
			age = age + amount;
			if (age > max_age) {
				amount = amount - (age - max_age);
				age = max_age;
			}
			stand.teleport(stand.getLocation().clone().add(0, amount * 0.135, 0));
			loc.getWorld().spawnParticle(Particle.COMPOSTER, getLocation().add(0.5, 0.5, 0.5), 10, 0.25, 0.25, 0.25, 0);
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

	public void harvest() {
		loc.getWorld().dropItemNaturally(loc, ci.getItem((age >= max_age ? new Random().nextInt(2) + 2 : 1)));
		loc.getBlock().setType(Material.AIR);
		Crops.removeCrop(loc);
	}

}
