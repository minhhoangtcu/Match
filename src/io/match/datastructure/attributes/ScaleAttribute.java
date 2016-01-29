package io.match.datastructure.attributes;

public class ScaleAttribute extends Attribute {
	
	private int from, to;
	private int choice;
	private static final int DIFF_0 = 100, DIFF_1 = 50, DIFF_2 = 20, DIFF_3 = 10, DIFF_4 = 0;
	
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
	
	public int getPoint(ScaleAttribute other) {
		
		float diffNormalized = Math.abs(getChoice() - other.getChoice())/(to-from);
		// If diffNormalized = 0, there is no differences between the two choice.
		if (0 <= diffNormalized && diffNormalized < 0.2)
			return DIFF_0;
		else if (0.2 <= diffNormalized && diffNormalized < 0.4)
			return DIFF_1;
		else if (0.4 <= diffNormalized && diffNormalized < 0.6)
			return DIFF_2;
		else if (0.6 <= diffNormalized && diffNormalized < 0.8)
			return DIFF_3;
		else
			return DIFF_4;
		
	}
	
	public int getMaxPoint() {
		return DIFF_0;
	}
	
	public ScaleAttribute setFrom(int from) {
		this.from = from;
		return this;
	}
	
	public ScaleAttribute setTo(int to) {
		this.to = to;
		return this;
	}
}
