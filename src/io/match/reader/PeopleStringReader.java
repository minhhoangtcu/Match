package io.match.reader;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.OneToMultipleAttribute;
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
		
		System.out.printf("name: %s\n", person.getName());
		
		for (Attribute attr : person.getAttributes()) {
			
			switch (attr.getAttributeType()) {
			case GENERAL:
				printDataGeneral(attr);
				break;
			case IGNORE:
				break;
			case WEIGHTED_ONE_TO_MULTIPLE:
				printDataOneToMultiple(attr);
				break;
			case WEIGHTED_SCALE:
				printDataScale(attr);
				break;
			}
		}
		
		System.out.printf("isMatched: %s\n", person.isMatched() ? "Already matched" : "Not matched");
		System.out.printf("Num matched: %d\tNum avaiable matches: %d\n", person.getNumMatched(), person.getNumMatchesAvaiable());
		System.out.printf("Matches: ");
		for (String name : person.getMatches()) {
			System.out.printf("%s ", name);
		}
		System.out.println();
		
	}
	
	private static void printDataGeneral(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), ((GeneralAttribute) input ).getData());
	}
	
	private static void printDataOneToMultiple(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), ((OneToMultipleAttribute) input ).getChoice());
	}
	
	private static void printDataScale(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), ((ScaleAttribute) input ).getChoice());
	}

}
