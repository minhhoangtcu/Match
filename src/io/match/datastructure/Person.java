package io.match.datastructure;

import java.util.LinkedList;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Person {

	// Name of the attribute and answer to this attribute
	private LinkedList<Attribute> attributes;
	private PersonType type;
	private int numMatchesAvaiable, numMatched;

	public Person() {
		attributes = new LinkedList<>();
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

	private String getAttribute(AttributeType type, String name) {
		for (Attribute attribute : attributes) {
			if (attribute.getAttributeName().equals(name)) {
				switch (type) {
				case GENERAL:
					return ((GeneralAttribute) attribute).getData();
				case IGNORE:
					break;
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

	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
}
