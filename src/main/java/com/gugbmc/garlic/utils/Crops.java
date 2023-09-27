package com.gugbmc.garlic.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;

public class Crops {

	private static Map<Location, CustomItem> crops = new HashMap<>();

	public static void addCrop(Location loc, CustomItem ci) {
		loc.getBlock().setMetadata("crop", new FixedMetadataValue(Utils.getPlugin(), ci));
		crops.put(loc, ci);
	}

	public static boolean isCrop(Location loc) {
		return loc.getBlock().hasMetadata("crop");
	}

	public static CustomItem getCrop(Location loc) {
		return (CustomItem) loc.getBlock().getMetadata("crop").get(0).value();
	}

	public static void removeCrop(Location loc) {
		loc.getBlock().removeMetadata("crop", Utils.getPlugin());
		crops.remove(loc);
	}

}
