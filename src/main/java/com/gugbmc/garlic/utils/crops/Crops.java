package com.gugbmc.garlic.utils.crops;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;

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

}
