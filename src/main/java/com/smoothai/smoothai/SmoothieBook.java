package com.smoothai.smoothai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SmoothieBook {

	public List<SmoothieRecipe> matching(List<String> ingredients) {
		//TODO J has to implement this.
		List<SmoothieRecipe> recipes = new LinkedList();
		Map<String, String> ingredientsOne = new HashMap<>();
		Map<String, String> ingredientsTwo = new HashMap<>();
		String[] preparationOne = {"Peel bananas", "Put into blender"};
		String[] preparationTwo = {"Peel apple", "Put into blender"};
		ingredientsOne.put("banana", "Two bananas");
		ingredientsOne.put("Ice", "One cup of ice");
		
		ingredientsTwo.put("Apple", "Two apples");
		SmoothieRecipe recipeOne = new SmoothieRecipe("Banana Smoothie", ingredientsOne, preparationOne, 1);
		SmoothieRecipe recipeTwo = new SmoothieRecipe("Appleton Smoothie", ingredientsTwo, preparationTwo, 1);
		
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		return recipes;
	}
	
}
