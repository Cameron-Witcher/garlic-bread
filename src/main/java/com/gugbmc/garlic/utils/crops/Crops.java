package com.gugbmc.garlic.utils.crops;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import com.gugbmc.garlic.utils.CustomItem;
import com.gugbmc.garlic.utils.Utils;

public class Crops {

	private static Map<Location, Crop> crops = new HashMap<>();

	public static void addCrop(Location loc, CustomItem ci) {
		crops.put(loc, new Crop(loc, ci));
	}

	public static boolean isCrop(Location loc) {
		return crops.containsKey(loc);
	}

	public static Crop getCrop(Location loc) {
		return crops.get(loc);
	}

	public static void removeCrop(Location loc) {
		crops.get(loc).kill();
		crops.remove(loc);
	}

	public static void removeAllCrops() {
		for (Entry<Location, Crop> e : crops.entrySet())
			e.getValue().kill();
		crops.clear();
	}

	public static void loadCrops() {
		FileConfiguration fc = Utils.getPlugin().getConfig();
		if (!(fc == null))
			for (String locstr : fc.getConfigurationSection("crops").getKeys(false)) {
				Location loc = Utils.decryptLocation(locstr);
				Crop crop = new Crop(loc, CustomItem.valueOf(fc.getString("crops." + locstr + ".custom_item")));
				crop.setAge(fc.getInt("crops." + locstr + ".age"));
				crops.put(loc, crop);
			}
	}

	public static void saveCrops() {
		FileConfiguration fc = Utils.getPlugin().getConfig();
		for (Entry<Location, Crop> e : crops.entrySet()) {
			fc.set("crops." + Utils.encryptLocation(e.getKey()) + ".custom_item", e.getValue().getCustomItem().name());
			fc.set("crops." + Utils.encryptLocation(e.getKey()) + ".age", e.getValue().getAge());
		}

		Utils.getPlugin().saveConfig();
	}

}
