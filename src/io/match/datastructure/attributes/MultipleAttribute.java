package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleAttribute extends Attribute {
	
	private HashSet<String> choices;
	private String choice;
	
	public MultipleAttribute(String name) {
		super(name);
		choices = new HashSet<>();
		attributeType = AttributeType.WEIGHTED_MULTIPLE;
	}

	public MultipleAttribute add(String param) {
		choices.add(param);
		return this;
	}
	
	public boolean isSame(MultipleAttribute other) {
		return getChoice().equals(other.getChoice());
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
}
