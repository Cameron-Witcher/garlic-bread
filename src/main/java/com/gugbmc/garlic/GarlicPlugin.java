package com.gugbmc.garlic;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.gugbmc.garlic.commands.AdminCommands;
import com.gugbmc.garlic.listeners.CropListener;

public class GarlicPlugin extends JavaPlugin implements Listener {

	public void onEnable() {
		new AdminCommands(this, "update");
		new CropListener(this);
	}

}
