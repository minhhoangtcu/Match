package io.match.datastructure;

import java.util.LinkedList;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;
import io.match.reader.PeopleStringReader;

public class Person {

	// Name of the attribute and answer to this attribute
	private String name;
	private LinkedList<Attribute> attributes;
	private int numMatchesAvaiable;
	private LinkedList<String> matches;

	public Person(String name) {
		this.name = name; 
		numMatchesAvaiable = 1;
		attributes = new LinkedList<>();
		matches = new LinkedList<>();
	}

	public void addGeneralAttribute(GeneralAttribute attr) {
		attributes.add(attr);
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

	public void addMatch(String match) {
		matches.add(match);
	}

	public void addOneToMultipleAttribute(OneToMultipleAttribute attr) {
		attributes.add(attr);
	}


	/**
	 * Add an one to multiple attribute to this person
	 * 
	 * @param name name of the attribute
	 * @param choice the choice that this person has made
	 * @param expectingChoices the expected choices that this person want his/her matches to be
	 * @param interest level of importance of this attribute
	 */
	public void addOneToMultipleAttribute(String name, String choice, String[] possibleChoices, String[] expectingChoices, Interest interest) {
		OneToMultipleAttribute temp = new OneToMultipleAttribute(name);
		temp.setPossibleChoices(possibleChoices);
		temp.setChoice(choice);
		for (String expectingChoice : expectingChoices) {
			temp.addExpectingChoice(expectingChoice.trim());
		}
		temp.setInterst(interest);
		attributes.add(temp);
	}
	
	public void addScaleAttribute(ScaleAttribute attr) {
		attributes.add(attr);
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
	
	private String getAttributeData(AttributeType type, String name) {
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
	 * Modify the attribute of this person with the same name of the provided attribute.
	 * </p>
	 * 
	 * @param attr attribute to modify
	 */
	public void modifyAttribute(Attribute pAttr) {
		Attribute personAttr = getAttribute(pAttr.getAttributeName());
		
		switch (pAttr.getAttributeType()) {
		case GENERAL:
			((GeneralAttribute) personAttr).setData(PeopleStringReader.getDataGeneral(pAttr));
			break;
		case WEIGHTED_ONE_TO_MULTIPLE:
			OneToMultipleAttribute temp = (OneToMultipleAttribute) personAttr;
			OneToMultipleAttribute pTemp = (OneToMultipleAttribute) pAttr;
			
			temp.setChoice(pTemp.getChoice());
			temp.setExpectingChoice(pTemp.getExpectingChoice());
			temp.setInterst(pTemp.getInterst());
			
			break;
		case WEIGHTED_SCALE:
			ScaleAttribute temp2 = (ScaleAttribute) personAttr;
			ScaleAttribute pTemp2 = (ScaleAttribute) pAttr;
			
			temp2.setChoice(pTemp2.getChoice());
			temp2.setExpectingChoice(pTemp2.getExpectingChoice());
			temp2.setInterst(pTemp2.getInterst());
			
			break;
		}
	}
	
	private Attribute getAttribute(String attrName) {
		for (Attribute attribute : attributes) {
			if (attribute.getAttributeName().equals(attrName))
				return attribute;
		}
		throw new IllegalArgumentException("No attribute with such name: " + attrName);
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
		return getAttributeData(AttributeType.GENERAL, name);
	}
	
	public LinkedList<String> getMatches() {
		return matches;
	}

	public String getName() {
		return name;
	}
	public int getNumMatched() {
		return matches.size();
	}
	public int getNumMatchesAvaiable() {
		return numMatchesAvaiable;
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
		return getAttributeData(AttributeType.WEIGHTED_ONE_TO_MULTIPLE, name);
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
		return Integer.parseInt(getAttributeData(AttributeType.WEIGHTED_SCALE, name));
	}
	public boolean isMatched() {
		return getNumMatched() == numMatchesAvaiable;
	}
	public void removeMatch(String match) {
		if (matches.isEmpty())
			throw new IllegalArgumentException("No matches to remove");
		if (!matches.remove(match)) {
			throw new IllegalArgumentException("Cannout find the provided person to remove");
		}
	}
	public void setAllMatch(LinkedList<String> matches) {
		if (matches.size() <= numMatchesAvaiable)
			this.matches = matches;
		else
			throw new IllegalArgumentException("Cannot set number of matches larger than number of avaiable matches");
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumMatchesAvaiable(int numMatchesAvaiable) {
		this.numMatchesAvaiable = numMatchesAvaiable;
	}
}
