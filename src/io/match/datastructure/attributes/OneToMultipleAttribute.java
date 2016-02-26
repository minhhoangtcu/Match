package io.match.datastructure.attributes;

import java.util.HashSet;

public class OneToMultipleAttribute extends Attribute implements Weightable<String>, Expectable<HashSet<String>>, Interestable {
	
	private String choice;
	private HashSet<String> possibleChoices;
	private HashSet<String> expectingChoices;
	private Interest interest;
	
	public OneToMultipleAttribute(String name) {
		super(name);
		possibleChoices = new HashSet<>();
		expectingChoices = new HashSet<>();
		attributeType = AttributeType.WEIGHTED_ONE_TO_MULTIPLE;
	}

	public OneToMultipleAttribute addPossibleChoice(String param) {
		// TODO: write a case to control the input
		possibleChoices.add(param);
		return this;
	}
	
	public OneToMultipleAttribute addExpectingChoice(String param) {
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

	public HashSet<String> getPossibleChoices() {
		return possibleChoices;
	}
}
