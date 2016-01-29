package io.match.datastructure.attributes;

public class ScaleAttribute extends Attribute {
	
	private int from, to;
	private int choice;
	
	public ScaleAttribute(String name) {
		super(name);
		weight = Weight.WEIGHTED;
		attributeType = AttributeType.SCALE;
	}
	
	public void setChoice(int choice) {
		if (choice <= to && choice >= from)
			this.choice = choice;
		else
			throw new IllegalArgumentException("The provided choice is out of range");
	}
	
	public int getChoice() {
		return choice;
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
