package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleChoices extends Attribute {
	
	private HashSet<String> choices;
	
	public MultipleChoices(String name) {
		super(name);
		choices = new HashSet<>();
		attributeType = AttributeType.MULTIPLE;
	}

	public MultipleChoices add(String param) {
		choices.add(param);
		return this;
	}
}
