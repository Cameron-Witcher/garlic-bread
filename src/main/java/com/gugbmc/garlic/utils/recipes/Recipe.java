package com.gugbmc.garlic.utils.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.gugbmc.garlic.utils.items.ItemIdentifier;

public class Recipe {

	String name;
	ItemStack result;
	String recipe;
	RecipeBuilder builder;

	public Recipe(String name, ItemStack result) {
		this.name = name;
		this.result = result;
	}

	public RecipeBuilder getBuilder() {
		builder = new RecipeBuilder();
		return builder;
	}

	class RecipeBuilder {
		String recipe = "";

		public RecipeBuilder addItem(ItemStack i) {
			if (i == null)
				i = new ItemStack(Material.AIR);
			recipe = recipe + ItemIdentifier.getId(i);
			return this;
		}

		public void build() {
			Recipe.this.recipe = recipe;
		}
	}

	public ItemStack getResult() {
		return result;
	}

	public String getRecipe() {
		return recipe;
	}

}
