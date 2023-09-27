package com.gugbmc.garlic;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.gugbmc.garlic.commands.AdminCommands;

public class GarlicPlugin extends JavaPlugin implements Listener {

	public void onEnable() {
		new AdminCommands(this, "update");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("This is a test join message.");
	}

}
