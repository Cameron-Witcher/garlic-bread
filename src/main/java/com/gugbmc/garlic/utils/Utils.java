package com.gugbmc.garlic.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import com.gugbmc.garlic.utils.crops.Crops;
import com.gugbmc.garlic.utils.items.ItemIdentifier;
import com.gugbmc.garlic.utils.recipes.RecipeUtils;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	private static Plugin plugin;

	static List<UUID> debuggers = new ArrayList<>();

	public static void init(Plugin main) {
		plugin = main;
		Crops.loadCrops();
		ItemIdentifier.init();
		RecipeUtils.init();
	}

	public static void end() {
		Crops.saveCrops();
	}

	public static boolean toggleDebug(UUID uid) {
		if (!debuggers.contains(uid))
			debuggers.add(uid);
		else
			debuggers.remove(uid);
		return debuggers.contains(uid);
	}

	public static void log(String message) {
		log(Level.ALL, message);
	}

	public static void log(Level level, String message) {
		Bukkit.getLogger().log(level, message);
		for (UUID uid : debuggers)
			if (Bukkit.getPlayer(uid) != null)
				Bukkit.getPlayer(uid).sendMessage(colorize(getLevelPrefix(level) + message));
	}

	private static String getLevelPrefix(Level level) {
		return "&7[&cgarlic-bread&7] &f";
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static String encryptLocation(Location loc) {
		String r = loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":"
				+ loc.getPitch() + ":" + loc.getYaw();
		r = r.replaceAll("\\.", ",");
		r = "location:" + r;
		return r;
	}

	public static Location decryptLocation(String s) {
		log("Decrypting Location: " + s);
		if (s.startsWith("location:"))
			s = s.replaceAll("location:", "");

		if (s.contains(","))
			s = s.replaceAll(",", ".");
		String[] args = s.split(":");
		Location r = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]),
				Double.parseDouble(args[3]));
		if (args.length >= 5) {
			r.setPitch(Float.parseFloat(args[4]));
			r.setYaw(Float.parseFloat(args[5]));
		}
		return r;
	}

	public static boolean updatePlugin() {

		String filename = "garlic-bread.jar";
		String url = "https://downloads.vanillaflux.com/plugins/" + filename;

		return downloadFile(url, "plugins/" + filename, "QuickScythe", "r6Pt#BF#Lg73@s4t");
	}

	public static boolean downloadFile(String url, String filename, String... auth) {
		log("Starting download of latest file");

		boolean success = true;
		InputStream in = null;
		FileOutputStream out = null;

		try {

			URL myUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
			log("Connected.");
			conn.setDoOutput(true);
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestMethod("GET");

			if (auth != null && auth.length >= 2) {
				String userCredentials = auth[0].trim() + ":" + auth[1].trim();
				String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
				conn.setRequestProperty("Authorization", basicAuth);
			}
			log("Authorization passed.");
			in = conn.getInputStream();
			out = new FileOutputStream(filename);
			int c;
			byte[] b = new byte[1024];
			log("Downloading...");
			while ((c = in.read(b)) != -1)
				out.write(b, 0, c);

		}

		catch (Exception ex) {
			log(("There was an error downloading " + filename + ". Check console for details."));
			ex.printStackTrace();
			success = false;
		}

		finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					log(("There was an error downloading " + filename + ". Check console for details."));
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					log(("There was an error downloading " + filename + ". Check console for details."));
					e.printStackTrace();
				}
		}
		log("Finished downloading update. You may reload now for changes to take effect, however you should restart at your next convenience.");
		return success;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

}
