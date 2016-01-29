package io.match.datastructure;

import java.util.LinkedList;

import io.match.datastructure.attributes.Attribute;

public class Person {
	
	// Name of the attribute and answer to this attribute
	LinkedList<Attribute> attributes;
	
	public Person() {
		attributes = new LinkedList<>();
	}
	
	public LinkedList<Attribute> getAttributes() {
		return attributes;
	}
	
	public void setAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
}
