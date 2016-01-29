package io.match.datastructure;

public class Faculty extends Person {
	
	private int numberOfMentees;
	
	public Faculty() {
		super();
		numberOfMentees = 1;
	}
	
	public void setNumberOfMentees(int numberOfMentees) {
		this.numberOfMentees = numberOfMentees;
	}

	public int getNumberOfMentees() {
		return numberOfMentees;
	}
}
