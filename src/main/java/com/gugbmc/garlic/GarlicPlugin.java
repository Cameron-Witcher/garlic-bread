package com.gugbmc.garlic;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.gugbmc.garlic.commands.AdminCommands;
import com.gugbmc.garlic.listeners.CropListener;
import com.gugbmc.garlic.utils.Utils;

public class GarlicPlugin extends JavaPlugin implements Listener {

	public void onEnable() {

		Utils.init(this);
		
		new AdminCommands(this, "update", "item", "debug");
		new CropListener(this);

	}

}
