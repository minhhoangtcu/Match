package io.match.reader;

import java.util.HashSet;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

/**
 * Provide easy way to read attribute of any person.
 * 
 */
public class PeopleStringReader {

	/**
	 * Print out the information of a person onto the console. This serves as a
	 * debugging method
	 * 
	 * @param person provided person
	 */
	public static void print(Person person) {

		System.out.printf("name: %s\n", person.getName());

		for (Attribute attr : person.getAttributes()) {

			switch (attr.getAttributeType()) {
			case GENERAL:
				printDataGeneral(attr);
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
		System.out.printf("Num matched: %d\tNum avaiable matches: %d\n", person.getNumMatched(),
				person.getNumMatchesAvaiable());
		System.out.printf("Matches: ");
		for (String name : person.getMatches()) {
			System.out.printf("%s ", name);
		}
		System.out.println();

	}

	public static String getDataGeneral(Attribute input) {
		return ((GeneralAttribute) input).getData();
	}

	public static String getDataOneToMultiple(Attribute input) {
		return ((OneToMultipleAttribute) input).getChoice();
	}
	
	public static HashSet<String> getExpectingOneToMultiple(Attribute input) {
		return ((OneToMultipleAttribute) input).getExpectingChoice();
	}
	
	public static String getImportanceOneToMultiple(Attribute input) {
		return IOUtil.getString(((OneToMultipleAttribute) input).getInterst());
	}

	public static int getDataScale(Attribute input) {
		return ((ScaleAttribute) input).getChoice();
	}
	
	public static int getExpectingScale(Attribute input) {
		return ((ScaleAttribute) input).getExpectingChoice();
	}
	
	public static String getImportanceScale(Attribute input) {
		return IOUtil.getString(((ScaleAttribute) input).getInterst());
	}

	private static void printDataGeneral(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), getDataGeneral(input));
	}

	private static void printDataOneToMultiple(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), getDataOneToMultiple(input));
	}

	private static void printDataScale(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), getDataScale(input));
	}
}
