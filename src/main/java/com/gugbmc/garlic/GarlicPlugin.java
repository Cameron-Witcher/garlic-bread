package com.gugbmc.garlic;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GarlicPlugin extends JavaPlugin {
	
	public void onEnable() {
		Bukkit.getLogger().log(Level.ALL, "Plugin Enabled.");
	}

}
