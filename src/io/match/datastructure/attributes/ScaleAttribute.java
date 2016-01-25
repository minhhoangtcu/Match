package io.match.datastructure.attributes;

public class ScaleAttribute extends Attribute {
	
	private int from, to;
	
	public ScaleAttribute(String name) {
		super(name);
		weight = Weight.WEIGHTED;
		attributeType = AttributeType.SCALE;
	}
	
	public ScaleAttribute setFrom(int from) {
		this.from = from;
		return this;
	}
	
	public ScaleAttribute setTo(int to) {
		this.to = to;
		return this;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

}
