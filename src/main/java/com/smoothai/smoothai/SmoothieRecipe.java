package com.smoothai.smoothai;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmoothieRecipe {
	private final Map<String, String> ingredients;
	private final String[] preparation;
	private final int serves;

	@JsonCreator
	public SmoothieRecipe(
			@JsonProperty("ingredients") final Map<String, String> ingredients,
			@JsonProperty("preparation") final String[] preparation, 
			@JsonProperty("serves") final int serves) {
		super();
		this.ingredients = ingredients;
		this.preparation = preparation;
		this.serves = serves;
	}

	public Map<String, String> getIngredients() {
		return ingredients;
	}

	public String[] getPreparation() {
		return preparation;
	}

	public int getServes() {
		return serves;
	}
	
	public List<String> matches(final List<String> ingredients) {
		return this.ingredients.keySet().stream().filter((String ingredient) -> 
			ingredients != null && ingredients.contains(ingredient)
		).collect(Collectors.toList());
	}
}
