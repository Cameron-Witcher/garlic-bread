package com.gugbmc.garlic.utils.recipes;

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

	public RecipeBuilder buildRecipe() {
		builder = new RecipeBuilder();
		return builder;
	}

	public void build() {
		recipe = builder.recipe;
	}

	class RecipeBuilder {
		String recipe = "";

		public RecipeBuilder addItem(ItemStack i) {
			recipe = recipe + ItemIdentifier.getId(i);
			return this;
		}
	}

	public ItemStack getResult() {
		return result;
	}

	public String getRecipe() {
		return recipe;
	}

}
