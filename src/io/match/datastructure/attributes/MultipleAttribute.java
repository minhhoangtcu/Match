package io.match.datastructure.attributes;

import java.util.HashSet;

public class MultipleAttribute extends Attribute implements Weightable<String>, Expectable<HashSet<String>>, Interestable {
	
	private HashSet<String> choices;
	private String choice;
	private HashSet<String> expectingChoices;
	private Interest interest;
	
	public MultipleAttribute(String name) {
		super(name);
		choices = new HashSet<>();
		attributeType = AttributeType.WEIGHTED_MULTIPLE;
	}

	public MultipleAttribute add(String param) {
		// TODO: write a case to control the input
		choices.add(param);
		return this;
	}
	
	public MultipleAttribute addExpectingChoice(String param) {
		// TODO: write a case to control the input
		expectingChoices.add(param);
		return this;
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

	public HashSet<String> getExpectingChoice() {
		return expectingChoices;
	}

	public void setExpectingChoice(HashSet<String> expecting) throws IllegalArgumentException {
		for (String expectation : expecting) {
			addExpectingChoice(expectation);
		}
	}
}
