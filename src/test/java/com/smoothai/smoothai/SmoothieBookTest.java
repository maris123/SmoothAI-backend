package com.smoothai.smoothai;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SmoothieBookTest {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Mock
	private ResourceLoader resourceLoader;
	
	private SmoothieBook smoothieBook;
	
	@Before
	public void setUpRecipes() throws IOException {
		Resource resource = mock(Resource.class);
		@SuppressWarnings("deprecation")
		InputStream inputStream = new StringBufferInputStream(MAPPER.writeValueAsString(Arrays.asList(
				buildRecipe("inam1", "ingredient value 1", "preparation 1", 1),
				buildRecipe("inam2", "ingredient value 2", "preparation 2", 2),
				buildRecipe("inam3", "ingredient value 3", "preparation 3", 3)
		)) + "\n");
		when(resource.getInputStream()).thenReturn(inputStream);
		when(resourceLoader.getResource(anyString())).thenReturn(resource);
		smoothieBook = new SmoothieBook(resourceLoader);
	}
	
	@Test
	public void returnsMatchingRecipes() {
		List<SmoothieRecipe> recipes = smoothieBook.matching(Arrays.asList("inam1"));
		assertEquals(1, recipes.size());
		assertEquals("ingredient value 1", recipes.get(0).getIngredients().get("inam1"));
	}
	
	@Test
	public void returnsEmptyListWhenNoMatch() {
		List<SmoothieRecipe> recipes = smoothieBook.matching(Arrays.asList("nosuchingredient"));
		assertEquals(0, recipes.size());
	}
	
	@Test
	public void returnsEmptyListForNullIngredients() {
		List<SmoothieRecipe> recipes = smoothieBook.matching(null);
		assertEquals(0, recipes.size());
	}

	private SmoothieRecipe buildRecipe(String ingredientName, String ingredientValue, String preparationLine, int serves) {
		Map<String, String> ingredients = new HashMap<>();
		ingredients.put(ingredientName, ingredientValue);
		return new SmoothieRecipe(ingredients, new String[]{preparationLine}, serves);
	}
	
}
