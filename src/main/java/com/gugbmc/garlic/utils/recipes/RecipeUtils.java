package com.gugbmc.garlic.utils.recipes;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.gugbmc.garlic.utils.items.CustomItem;

public class RecipeUtils {

	static Map<String, Recipe> recipes = new HashMap<>();

	public static void init() {
		Recipe rec = createRecipe("garlic_bread", CustomItem.GARLIC_BREAD.getItem());
		rec.buildRecipe().addItem(null).addItem(CustomItem.GARLIC.getItem()).addItem(null).addItem(null)
				.addItem(new ItemStack(Material.BREAD)).addItem(null).addItem(null).addItem(null).addItem(null);
		rec.build();
	}

	public static Recipe createRecipe(String name, ItemStack result) {
		Recipe rec = new Recipe(name, result);
		recipes.put(name, rec);
		return rec;
	}

	public static Map<String, Recipe> getRecipes() {
		return recipes;
	}

}
