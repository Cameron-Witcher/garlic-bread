package com.gugbmc.garlic.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gugbmc.garlic.utils.Utils;
import com.gugbmc.garlic.utils.items.CustomItem;

public class AdminCommands implements CommandExecutor {

	public AdminCommands(JavaPlugin plugin, String... cmds) {
		for (String cmd : cmds) {
			plugin.getCommand(cmd).setExecutor(this);
//			plugin.getCommand(cmd).setTabCompleter(new PlayerTabCompleter());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("update")) {
			Utils.updatePlugin();
		}
		if (cmd.getName().equalsIgnoreCase("item")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.getInventory().addItem(CustomItem.GARLIC.getItem());
				player.updateInventory();
			}
		}
		if (cmd.getName().equalsIgnoreCase("debug")) {
			if (sender instanceof Player) {
				sender.sendMessage(Utils.colorize(
						"&7[&cgarlic-bread&7]&f Debug mode: " + Utils.toggleDebug(((Player) sender).getUniqueId())));
			}
		}
		return true;
	}
}