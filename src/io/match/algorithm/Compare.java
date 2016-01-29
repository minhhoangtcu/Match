package io.match.algorithm;

import java.util.Iterator;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.MultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Compare {

	/**
	 * Get the percentage matching from 2 people. This algorithm does not care about whether the person is a student or faculty.
	 * 
	 * @param first the first person
	 * @param second the second person
	 * @return
	 * @throws Exception 
	 */
	public static int getMatch(Person first, Person second) throws Exception {
		
		if (first.getAttributes().size() != second.getAttributes().size())
			throw new IllegalArgumentException("Numbers of attributes from 2 arguments do not match!");
		
		Iterator<Attribute> firstIterator = first.getAttributes().iterator();
		Iterator<Attribute> secondIterator = second.getAttributes().iterator();
		 
		
		for (int i = 0; i < first.getAttributes().size(); i++) {
			
			Attribute firstAttr = firstIterator.next();
			Attribute secondAttr = secondIterator.next();
			
			if (firstAttr instanceof GeneralAttribute && secondAttr instanceof GeneralAttribute) {
				
			}
			else if (firstAttr instanceof MultipleAttribute && secondAttr instanceof MultipleAttribute) {
				
			}
			else if (firstAttr instanceof ScaleAttribute && secondAttr instanceof ScaleAttribute) {
				
			}
			else
				throw new Exception("The attributes of 2 people do not match!");
			
		}
		
		return 0;
	}

}
