package com.smoothai.smoothai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SmoothieRecipeTest {

	private static final int SERVES = 14;
	private static final String[] PREPARATION = new String[] {"Step 1", "Step 2", "Step 3"};
	private static final Map<String, String> INGREDIENTS = new HashMap<>();
	static {
		INGREDIENTS.put("one", "one value");
		INGREDIENTS.put("two", "two value");
		INGREDIENTS.put("three", "three value");
	}
	
	private SmoothieRecipe recipe = new SmoothieRecipe(INGREDIENTS, PREPARATION, SERVES);
	
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
