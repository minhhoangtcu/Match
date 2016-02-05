package io.match.datastructure;

import java.util.LinkedList;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;

public class Person {
	
	// Name of the attribute and answer to this attribute
	LinkedList<Attribute> attributes;
	
	public Person() {
		attributes = new LinkedList<>();
	}
	
	public LinkedList<Attribute> getAttributes() {
		return attributes;
	}
	
	public String getGeneralAttribute(String name) {
		for (Attribute attribute : attributes) {
			if (attribute.getAttributeName().equals(name))
				return ((GeneralAttribute) attribute).getData();
		}
		throw new IllegalArgumentException(String.format("The person does not have attribute named %s", name));
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
}
