package com.gugbmc.garlic.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

public class Crops {

	private static Map<Location, CustomItem> crops = new HashMap<>();

	public static void addCrop(Location loc, CustomItem ci) {
		crops.put(loc, ci);
	}

	public static boolean isCrop(Location loc) {
		return crops.containsKey(loc);
	}

	public static CustomItem getCrop(Location loc) {
		return crops.get(loc);
	}

}
