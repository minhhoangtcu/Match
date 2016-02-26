package io.match.datastructure;

import java.util.LinkedList;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Person {

	// Name of the attribute and answer to this attribute
	private String name;
	private LinkedList<Attribute> attributes;
	private PersonType type;
	private int numMatchesAvaiable, numMatched;
	private boolean isMatched;
	private LinkedList<String> matches;

	public Person(String name) {
		this.name = name; 
		attributes = new LinkedList<>();
		matches = new LinkedList<>();
	}

	public Person toStudent() {
		type = PersonType.STUDENT;
		numMatchesAvaiable = 1;
		numMatched = 0;
		return this;
	}

	public Person toFalcuty(int max) {
		type = PersonType.FACULTY;
		numMatchesAvaiable = max;
		numMatched = 0;
		return this;
	}

	/**
	 * <p>
	 * Get all attributes from this person. This method is best used for
	 * printing out all the information. Please do remember to use a switch case
	 * to handle the attribute types.
	 * </p>
	 * 
	 * @return all attributes from this person
	 */
	public LinkedList<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * <p>
	 * Get the data from provided attribute name. Please only enter the name
	 * similarly to what declared on the attributes file. Also, avoid using this
	 * method because it has to search linearly for the attribute.
	 * </p>
	 * 
	 * @param name
	 *            name of the attribute as provided in the attribute file
	 * @return the data the attribute contains
	 */
	public String getGeneralAttribute(String name) {
		return getAttribute(AttributeType.GENERAL, name);
	}

	/**
	 * <p>
	 * Get the data from provided attribute name. Please only enter the name
	 * similarly to what declared on the attributes file. Also, avoid using this
	 * method because it has to search linearly for the attribute.
	 * </p>
	 * 
	 * @param name
	 *            name of the attribute as provided in the attribute file
	 * @return the choice this person made
	 */
	public String getOneToMultipleAttribute(String name) {
		return getAttribute(AttributeType.WEIGHTED_ONE_TO_MULTIPLE, name);
	}

	/**
	 * <p>
	 * Get the data from provided attribute name. Please only enter the name
	 * similarly to what declared on the attributes file. Also, avoid using this
	 * method because it has to search linearly for the attribute.
	 * </p>
	 * 
	 * @param name
	 *            name of the attribute as provided in the attribute file
	 * @return the level this person picked
	 */
	public int getScaleAttribute(String name) {
		return Integer.parseInt(getAttribute(AttributeType.WEIGHTED_SCALE, name));
	}


	/**
	 * Add a general attribute to this person
	 * 
	 * @param name name of the attribute
	 * @param data contained data of the attribute
	 */
	public void addGeneralAttribute(String name, String data) {
		attributes.add(new GeneralAttribute(name, data));
	}
	
	/**
	 * Add an one to multiple attribute to this person
	 * 
	 * @param name name of the attribute
	 * @param choice the choice that this person has made
	 * @param expectingChoices the expected choices that this person want his/her matches to be
	 * @param interest level of importance of this attribute
	 */
	public void addOneToMultipleAttribute(String name, String choice, String[] expectingChoices, Interest interest) {
		OneToMultipleAttribute temp = new OneToMultipleAttribute(name);
		temp.setChoice(choice);
		for (String expectingChoice : expectingChoices) {
			temp.addExpectingChoice(expectingChoice);
		}
		temp.setInterst(interest);
		attributes.add(temp);
	}
	
	/**
	 * Add a scale attribute to this person
	 * 
	 * @param name name of the attribute
	 * @param from the lower bound
	 * @param to the upper bound
	 * @param choice the choice of this person
	 * @param expectingChoice the expecting choice that this person want his/her matches to be
	 * @param interest level of importance of this attribute
	 */
	public void addScaleAttribute(String name, int from, int to, int choice, int expectingChoice, Interest interest) {
		ScaleAttribute temp = new ScaleAttribute(name).setFrom(from).setTo(to);
		temp.setChoice(choice);
		temp.setExpectingChoice(expectingChoice);
		temp.setInterst(interest);
		attributes.add(temp);
	}
	
	private String getAttribute(AttributeType type, String name) {
		name = name.toLowerCase();
		for (Attribute attribute : attributes) {
			if (attribute.getAttributeName().toLowerCase().equals(name)) {
				switch (type) {
				case GENERAL:
					return ((GeneralAttribute) attribute).getData();
				case WEIGHTED_ONE_TO_MULTIPLE:
					return ((OneToMultipleAttribute) attribute).getChoice();
				case WEIGHTED_SCALE:
					return ((ScaleAttribute) attribute).getChoice().toString();
				default:
					throw new IllegalArgumentException("Invalid attribute type");
				}
			}
		}
		throw new IllegalArgumentException(String.format("The person does not have attribute named %s", name));
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PersonType getType() {
		return type;
	}
	public void setType(PersonType type) {
		this.type = type;
	}
	public int getNumMatched() {
		return numMatched;
	}
	public void setNumMatched(int numMatched) {
		this.numMatched = numMatched;
	}
	public int getNumMatchesAvaiable() {
		return numMatchesAvaiable;
	}
	public void setNumMatchesAvaiable(int numMatchesAvaiable) {
		this.numMatchesAvaiable = numMatchesAvaiable;
	}
	public boolean isMatched() {
		return isMatched;
	}
	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}
	public LinkedList<String> getMatches() {
		return matches;
	}
	public void setAllMatch(LinkedList<String> matches) {
		this.matches = matches;
	}
	public void addMatch(String match) {
		matches.add(match);
	}
	public void removeMatch(String match) {
		if (matches.isEmpty())
			throw new IllegalArgumentException("No matches to remove");
		if (!matches.remove(match)) {
			throw new IllegalArgumentException("Cannout find the provided person to remove");
		}
	}
}
