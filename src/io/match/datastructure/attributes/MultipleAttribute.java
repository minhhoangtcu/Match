package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleAttribute extends Attribute {
	
	private HashSet<String> choices;
	private String choice;
	
	public MultipleAttribute(String name) {
		super(name);
		choices = new HashSet<>();
		weight = Weight.WEIGHTED;
		attributeType = AttributeType.MULTIPLE;
	}

	public MultipleAttribute add(String param) {
		choices.add(param);
		return this;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
}
