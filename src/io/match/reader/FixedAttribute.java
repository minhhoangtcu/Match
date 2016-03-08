package io.match.reader;

import java.util.LinkedList;

/**
 * We load the fixed attribute file individually; thus, we need to create a
 * class to hold all the data before assigning them into people
 */
class FixedAttribute {

	private int numMatchesAvaiable, numMatched;
	private boolean isMatched;
	private LinkedList<String> matches;

	public FixedAttribute(boolean isMatched, int numMatchesAvaiable, int numMatched) {
		matches = new LinkedList<>();
		this.isMatched = isMatched;
		this.numMatchesAvaiable= numMatchesAvaiable;
		this.numMatched = numMatched;
	}
	
	public void addMatch(String match) {
		matches.add(match);
	}
	public int getNumMatchesAvaiable() {
		return numMatchesAvaiable;
	}
	public int getNumMatched() {
		return numMatched;
	}
	public boolean isMatched() {
		return isMatched;
	}
	public LinkedList<String> getMatches() {
		return matches;
	}
}