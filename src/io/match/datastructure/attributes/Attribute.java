package io.match.datastructure.attributes;

public class Attribute {
	
	protected String attributeName;
	protected AttributeType attributeType;
	protected boolean isIgnored;
	
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
		isIgnored = false;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
	public AttributeType getAttributeType() {
		return attributeType;
	}

	public boolean isIgnored() {
		return isIgnored;
	}

	public Attribute setIgnored(boolean isIgnored) {
		this.isIgnored = isIgnored;
		this.attributeType = AttributeType.IGNORE;
		return this;
	}
}
