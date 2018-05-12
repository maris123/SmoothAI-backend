package com.smoothai.smoothai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmoothieIngredient {
	private final String name;
	private final String value;
	
	@JsonCreator
	public SmoothieIngredient(@JsonProperty("name") final String name, @JsonProperty("value") final String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
}
