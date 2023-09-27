package com.gugbmc.garlic;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GarlicPlugin extends JavaPlugin implements Listener {
	
	public void onEnable() {
		Bukkit.getLogger().log(Level.ALL, "Plugin Enabled.");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("This is a test join message.");
	}

}
