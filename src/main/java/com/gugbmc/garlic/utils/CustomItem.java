package com.gugbmc.garlic.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItem {

	GARLIC("Garlic", Material.CARROT, 50), GARLIC_BREAD("Garlic Bread", Material.BREAD, 1);

	String name;
	int cmd;
	Material mat;

	CustomItem(String name, Material mat, int cmd) {
		this.name = name;
		this.mat = mat;
		this.cmd = cmd;
	}

	public ItemStack getItem() {
		return getItem(1);
	}

	public ItemStack getItem(int amount) {
		ItemStack ci = new ItemStack(mat);
		ItemMeta im = ci.getItemMeta();
		im.setCustomModelData(cmd);
		im.setDisplayName(name);
		ci.setItemMeta(im);
		ci.setAmount(amount);
		return ci;
	}

}
