package com.smoothai.smoothai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SmoothieRecipeTest {

	private static final String RECIPE_NAME = "RNAME";
	private static final int SERVES = 14;
	private static final String[] PREPARATION = new String[] {"Step 1", "Step 2", "Step 3"};
	private static final List<SmoothieIngredient> INGREDIENTS = new ArrayList<>();
	static {
		INGREDIENTS.add(new SmoothieIngredient("one", "one value"));
		INGREDIENTS.add(new SmoothieIngredient("two", "two value"));
		INGREDIENTS.add(new SmoothieIngredient("three", "three value"));
	}
	
	private SmoothieRecipe recipe = new SmoothieRecipe(RECIPE_NAME, INGREDIENTS, PREPARATION, SERVES);
	
	@Test
	public void returnEmptyListWhenNoMatch() {
		assertEquals(0, recipe.matches(Arrays.asList("four", "five")).size());
	}
	
	@Test
	public void returnsMatchesWhenMatch() {
		List<String> matches = recipe.matches(Arrays.asList("one", "three", "five"));
		assertEquals(2, matches.size());
		assertTrue(matches.contains("one"));
		assertTrue(matches.contains("three"));
	}
	
	@Test
	public void doesNotFailWithEmptyIngredientsList() {
		recipe.matches(null);
	}
}
