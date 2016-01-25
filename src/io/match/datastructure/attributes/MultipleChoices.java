package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleChoices {
	
	private HashSet<String> choices;
	private String name;
	
	public MultipleChoices(String name) {
		this.name = name;
		choices = new HashSet<>();
	}

	public MultipleChoices add(String param) {
		choices.add(param);
		return this;
	}
	
	public String getName() {
		return name;
	}
}
