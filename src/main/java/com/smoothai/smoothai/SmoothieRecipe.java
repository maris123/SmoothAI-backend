package com.smoothai.smoothai;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmoothieRecipe {
	private final Map<String, String> ingredients;
	private final String[] preparation;
	private final int serves;

	@JsonCreator
	public SmoothieRecipe(
			@JsonProperty("ingredients") final Map<String, String> ingredients,
			@JsonProperty("ingredients") final String[] preparation, 
			@JsonProperty("ingredients") final int serves) {
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
}
