package com.smoothai.smoothai;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SmoothieBook {

	private List<SmoothieRecipe> allRecipes;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public SmoothieBook(@Autowired final ResourceLoader resourceLoader) {
		
		try {
			allRecipes = Collections.unmodifiableList(MAPPER.readValue(
					resourceLoader.getResource("classpath:recipes.json").getInputStream(),
					MAPPER.getTypeFactory().constructCollectionType(
			                List.class, SmoothieRecipe.class)));
		} catch (IOException e) {
			allRecipes = Collections.emptyList();
			e.printStackTrace();
		}
	}
	
	public List<SmoothieRecipe> matching(List<String> ingredients) {
		return allRecipes.stream().filter((SmoothieRecipe recipe) -> 
			!recipe.matches(ingredients).isEmpty()
		).sorted((SmoothieRecipe recipe1, SmoothieRecipe recipe2) -> 
				// With minus in order to obtain reverse ordering
				 -Integer.compare(recipe1.matches(ingredients).size(), recipe2.matches(ingredients).size())
		).collect(Collectors.toList());
	}
}
