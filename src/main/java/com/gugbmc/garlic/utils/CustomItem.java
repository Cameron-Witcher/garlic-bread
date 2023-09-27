package com.gugbmc.garlic.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItem {

	GARLIC(Material.CARROT, 1), GARLIC_BREAD(Material.BREAD, 1);

	int cmd;
	Material mat;

	CustomItem(Material mat, int cmd) {
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
		ci.setItemMeta(im);
		ci.setAmount(1);
		return ci;
	}

}
