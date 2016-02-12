package io.match.datastructure.attributes;

public class ScaleAttribute extends Attribute implements Weightable<Integer>, Expectable<Integer>, Interestable {
	
	private int from, to;
	private int choice, expectingChoice;
	private Interest interest;
	
	public ScaleAttribute(String name) {
		super(name);
		attributeType = AttributeType.WEIGHTED_SCALE;
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
	
	public void setChoice(Integer choice) throws IllegalArgumentException {
		if (choice <= to && choice >= from)
			this.choice = choice;
		else
			throw new IllegalArgumentException("The provided choice is out of range");
	}
	
	public Integer getChoice() {
		return choice;
	}
	
	public Integer getExpectingChoice() {
		return expectingChoice;
	}

	public void setExpectingChoice(Integer choice) throws IllegalArgumentException {
		if (choice <= to && choice >= from)
			this.expectingChoice = choice;
		else
			throw new IllegalArgumentException("The provided choice is out of range");

	}

	public Interest getInterst() {
		return interest;
	}

	public void setInterst(Interest interst) {
		this.interest = interst;
	}
}
