package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleAttribute extends Attribute implements Weightable<String>, Expectable<String>, Interestable {
	
	private HashSet<String> choices;
	private String choice, expectingChoice;
	private Interest interest;
	
	public MultipleAttribute(String name) {
		super(name);
		choices = new HashSet<>();
		attributeType = AttributeType.WEIGHTED_MULTIPLE;
	}

	public MultipleAttribute add(String param) {
		choices.add(param);
		return this;
	}
	
	public boolean isSame(MultipleAttribute other) {
		return getChoice().equals(other.getChoice());
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) throws IllegalArgumentException {
		this.choice = choice;
	}

	public Interest getInterst() {
		return interest;
	}

	public void setInterst(Interest interst) {
		this.interest = interst;
	}

	public String getExpectingChoice() {
		return expectingChoice;
	}

	@Override
	public void setExpectingChoice(String expecting) throws IllegalArgumentException {
		// TODO: write a case to control the input
		this.expectingChoice = expecting;
	}
}
