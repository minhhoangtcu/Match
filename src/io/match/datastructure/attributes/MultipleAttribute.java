package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleAttribute extends Attribute {
	
	private HashSet<String> choices;
	
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
}
