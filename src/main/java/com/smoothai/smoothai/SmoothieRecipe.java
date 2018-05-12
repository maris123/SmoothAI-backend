package com.smoothai.smoothai;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmoothieRecipe {
	private final String name;
	private final List<SmoothieIngredient> ingredients;
	private final String[] preparation;
	private final int serves;

	@JsonCreator
	public SmoothieRecipe(
			@JsonProperty("name") final String name,
			@JsonProperty("ingredients") final List<SmoothieIngredient> ingredients,
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
	
	public List<SmoothieIngredient> getIngredients() {
		return ingredients;
	}

	public String[] getPreparation() {
		return preparation;
	}

	public int getServes() {
		return serves;
	}
	
	public List<String> matches(final List<String> ingredients) {
		return this.ingredients.stream().map(SmoothieIngredient::getName).filter((String ingredient) -> 
			ingredients != null && ingredients.contains(ingredient)
		).collect(Collectors.toList());
	}
}
