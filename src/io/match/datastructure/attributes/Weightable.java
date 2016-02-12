package io.match.datastructure.attributes;

public interface Weightable <E> {
	
	public E getChoice();
	public void setChoice(E choice) throws IllegalArgumentException;
	
}
