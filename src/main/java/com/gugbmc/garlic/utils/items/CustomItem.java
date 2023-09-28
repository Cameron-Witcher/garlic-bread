package com.gugbmc.garlic.utils.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gugbmc.garlic.utils.Utils;

public enum CustomItem {

	GARLIC("Garlic", Material.CARROT, true, 99200001), GARLIC_BREAD("Garlic Bread", Material.BREAD, false, 1), STOVE("Stove", Material.IRON_NUGGET, false, 99200501);

	String name;
	int cmd;
	Material mat;
	boolean crop;

	CustomItem(String name, Material mat, boolean crop, int cmd) {
		this.name = name;
		this.mat = mat;
		this.cmd = cmd;
		this.crop = crop;
	}

	public ItemStack getItem() {
		return getItem(1);
	}

	public ItemStack getItem(int amount) {
		ItemStack ci = new ItemStack(mat);
		ItemMeta im = ci.getItemMeta();
		im.setCustomModelData(cmd);
		im.setDisplayName(Utils.colorize("&r" + name));
		ci.setItemMeta(im);
		ci.setAmount(amount);
		return ci;
	}

	public String getName() {
		return Utils.colorize(name);
	}

	public Integer getModelData() {
		return cmd;
	}

	public boolean isCrop() {
		return crop;
	}

}
