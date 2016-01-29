package io.match.datastructure;

import java.util.HashMap;

public class Person {
	
	// 
	HashMap<String, String> attributes;
	
	public Person() {
		attributes = new HashMap<>();
	}
	
	public String getAttribute(String attributeName) {
		if (attributes.containsKey(attributeName))
			return attributes.get(attributeName);
		else
			throw new IllegalArgumentException(String.format("Does not contain attribute named %s", attributeName));
	}
	
	public void setAttribute(String attributeName, String choice) {
		attributes.put(attributeName, choice);
	}
	
}
