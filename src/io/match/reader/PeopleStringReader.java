package io.match.reader;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.MultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

/**
 * This class served as a debugging class.
 * It will print the info of a person out to the console 
 * 
 * @author minhhoang
 *
 */
public class PeopleStringReader {
	
	public static void print(Person person) {
		
		for (Attribute attr : person.getAttributes()) {
			
			switch (attr.getAttributeType()) {
			case GENERAL:
				printDataGeneral(attr);
				break;
			case IGNORE:
				break;
			case WEIGHTED_MULTIPLE:
				printDataMultiple(attr);
				break;
			case WEIGHTED_SCALE:
				printDataScale(attr);
				break;
			}
		}
		System.out.println();
		
	}
	
	private static void printDataGeneral(Attribute input) {
		System.out.printf("%s ", ((GeneralAttribute) input ).getData());
	}
	
	private static void printDataMultiple(Attribute input) {
		System.out.printf("%s ", ((MultipleAttribute) input ).getChoice());
	}
	
	private static void printDataScale(Attribute input) {
		System.out.printf("%s ", ((ScaleAttribute) input ).getChoice());
	}

}