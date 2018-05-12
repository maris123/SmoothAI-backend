package com.smoothai.smoothai;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmoothieRecipe {
	private final String name;
	private final Map<String, String> ingredients;
	private final String[] preparation;
	private final int serves;

	@JsonCreator
	public SmoothieRecipe(
			@JsonProperty("name") final String name,
			@JsonProperty("ingredients") final Map<String, String> ingredients,
			@JsonProperty("preparation") final String[] preparation, 
			@JsonProperty("serves") final int serves) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.preparation = preparation;
		this.serves = serves;
	}

	public String getName() {
		return name;
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
