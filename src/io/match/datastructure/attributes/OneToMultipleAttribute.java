package io.match.datastructure.attributes;

import java.util.HashSet;

public class OneToMultipleAttribute extends Attribute
		implements Weightable<String>, Expectable<String[]>, Interestable {

	private String choice;
	private HashSet<String> possibleChoices;
	private HashSet<String> expectingChoices;
	private Interest interest;
	private int numOfPossibleChoices, numOfExpectingChoices;

	public OneToMultipleAttribute(String name) {
		super(name);
		possibleChoices = new HashSet<>();
		expectingChoices = new HashSet<>();
		attributeType = AttributeType.WEIGHTED_ONE_TO_MULTIPLE;
		numOfPossibleChoices = 0;
		numOfExpectingChoices = 0;
	}

	public OneToMultipleAttribute addPossibleChoice(String param) {
		possibleChoices.add(param);
		numOfPossibleChoices++;
		return this;
	}

	public OneToMultipleAttribute addExpectingChoice(String param) {
		if (possibleChoices.contains(param)) {
			expectingChoices.add(param);
			numOfExpectingChoices++;
			return this;
		} else
			throw new IllegalArgumentException(String.format("Expecting choice: %s is not one of the possible choices", param));
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) throws IllegalArgumentException {
		if (possibleChoices.contains(choice)) {
			this.choice = choice;
		} else
			throw new IllegalArgumentException(String.format("Choice: %s is not one of the possible choices", choice));
	}

	public Interest getInterst() {
		return interest;
	}

	public void setInterst(Interest interst) {
		this.interest = interst;
	}
	
	public void setPossibleChoices(String[] possible) {
		for (String p : possible) {
			addPossibleChoice(p);
		}
	}

	public void setExpectingChoice(String[] expecting) throws IllegalArgumentException {
		for (String expectation : expecting) {
			addExpectingChoice(expectation);
		}
	}
	
	public String[] getExpectingChoice() {
		return expectingChoices.toArray(new String[numOfExpectingChoices]);
	}

	public String[] getPossibleChoices() {
		return possibleChoices.toArray(new String[numOfPossibleChoices]);
	}

	public int getNumOfPossibleChoices() {
		return numOfPossibleChoices;
	}

	public int getNumOfExpectingChoices() {
		return numOfExpectingChoices;
	}

	public void setNumOfExpectingChoices(int numOfExpectingChoices) {
		this.numOfExpectingChoices = numOfExpectingChoices;
	}
}
