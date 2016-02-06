package io.match.datastructure.attributes;

public class Attribute {
	
	protected String attributeName;
	protected AttributeType attributeType;
	protected boolean isExpecting;
	protected Interest interst;
	
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
	public AttributeType getAttributeType() {
		return attributeType;
	}

	public boolean isExpecting() {
		return isExpecting;
	}

	public void setExpecting(boolean isExpecting) {
		this.isExpecting = isExpecting;
	}

	public Interest getInterst() {
		return interst;
	}

	public void setInterst(Interest interst) {
		this.interst = interst;
	}
	
	public enum AttributeType {
		IGNORE,
		GENERAL,
		WEIGHTED_MULTIPLE,
		WEIGHTED_SCALE;
	}
	
	public enum Interest {
		VERY_IMPORTANT,
		SOMEWHAT_IMPORTANT,
		NOT_IMPORTANT;
	}
}
