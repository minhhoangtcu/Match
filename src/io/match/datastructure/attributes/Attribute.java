package io.match.datastructure.attributes;

public class Attribute {
	
	protected String attributeName;
	protected AttributeType attributeType;
	protected Weight weight;
	protected boolean isExpecting;
	protected Interest interst;
	
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
		GENERAL,
		MULTIPLE,
		SCALE;
	}
	
	public enum Weight {
		IGNORE,
		GENERAL,
		WEIGHTED;
	}
	
	public enum Interest {
		VERY_IMPORTANT(100),
		SOMEWHAT_IMPORTANT(50),
		NOT_IMPORTANT(70);
		
		private int point;
		
		Interest(int point) {
			this.point = point;
		}

		public int getPoint() {
			return point;
		}
		
		
	}

	
}
