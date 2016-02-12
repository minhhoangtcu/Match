package io.match.datastructure.attributes;

public class Attribute {
	
	protected String attributeName;
	protected AttributeType attributeType;
	
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
	public AttributeType getAttributeType() {
		return attributeType;
	}
}
