package io.match.reader;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeUtil;
import io.match.datastructure.attributes.Expectable;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.Interestable;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;
import io.match.datastructure.attributes.Weightable;

/**
 * Provide easy way to read attribute of any person.
 * 
 */
public class PeopleStringReader {

	/**
	 * Print out the information of a person onto the console. This serves as a
	 * debugging method
	 * 
	 * @param person
	 *            provided person
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
		System.out.println();
	}
	
	public static <T extends Weightable<E>, E> E getChoice(T attr) {
		return attr.getChoice();
	}
	
	public static <T extends Attribute & Expectable<E>, E> E getExpecting(T attr) {
		return attr.getExpectingChoice();
	}
	
	public static <T extends Attribute & Interestable> Interest getInterest(T attr) {
		return attr.getInterst();
	}

	public static String getDataGeneral(Attribute input) {
		return ((GeneralAttribute) input).getData();
	}

	public static String getDataOneToMultiple(Attribute input) {
		return ((OneToMultipleAttribute) input).getChoice();
	}

	public static String[] getExpectingOneToMultiple(Attribute input) {
		return ((OneToMultipleAttribute) input).getExpectingChoice();
	}
	
	public static String[] getPossibleOneToMultiple(Attribute input) {
		return ((OneToMultipleAttribute) input).getPossibleChoices();
	}

	public static String getImportanceOneToMultiple(Attribute input) {
		return AttributeUtil.getStringFromInterest(((OneToMultipleAttribute) input).getInterst());
	}

	public static int getDataScale(Attribute input) {
		return ((ScaleAttribute) input).getChoice();
	}

	public static int getExpectingScale(Attribute input) {
		return ((ScaleAttribute) input).getExpectingChoice();
	}

	public static String getImportanceScale(Attribute input) {
		return AttributeUtil.getStringFromInterest(((ScaleAttribute) input).getInterst());
	}
	
	public static int getMinScale(Attribute input) {
		return ((ScaleAttribute) input).getFrom();
	}
	
	public static int getMaxScale(Attribute input) {
		return ((ScaleAttribute) input).getTo();
	}

	private static void printDataGeneral(Attribute input) {
		System.out.printf("%s: %s\n", input.getAttributeName(), getDataGeneral(input));
	}

	private static void printDataOneToMultiple(Attribute input) {
		System.out.printf("%s: %s\t", input.getAttributeName(), getDataOneToMultiple(input));
		for (String expectation : getExpectingOneToMultiple(input)) {
			System.out.printf("%s ", expectation);
		}
		System.out.printf("\tImportance: %s\n", getImportanceOneToMultiple(input));
	}

	private static void printDataScale(Attribute input) {
		System.out.printf("%s: %s\tExpecting: %s\tImportance: %s\n", input.getAttributeName(), getDataScale(input),
				getExpectingScale(input), getImportanceScale(input));
	}
}
