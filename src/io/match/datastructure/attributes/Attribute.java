package io.match.datastructure.attributes;

public class Attribute {
	
	protected String attributeName;
	protected AttributeType attributeType;
	protected Weight weight;
	
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
	public Weight getWeight() {
		return weight;
	}
	
	public AttributeType getAttributeType() {
		return attributeType;
	}

	enum AttributeType {
		MULTIPLE,
		SCALE
	}
	
	enum Weight {
		IGNORE,
		GENERAL,
		WEIGHTED
	}

	
}
