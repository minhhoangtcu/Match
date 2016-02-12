package io.match.datastructure.attributes;

public interface Expectable <E> {

	public E getExpectingChoice();
	public void setExpectingChoice(E expecting) throws IllegalArgumentException;
	
}
