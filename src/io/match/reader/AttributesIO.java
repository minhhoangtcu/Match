package io.match.reader;

import java.util.HashMap;
import java.util.LinkedList;

import io.match.datastructure.attributes.Attribute;

public class AttributesIO {

	private String dir;
	private LinkedList<String> attributeName;
	private HashMap<String, Attribute> attributes;

	public AttributesIO(String dir) {
		this.dir = dir;
		initAttribute();
	}

	/**
	 * <p>
	 * Read all attributes from the file and put them into both the linked list
	 * and the hash map
	 * <p>
	 */
	private void initAttribute() {

		// TODO: Finish the method, read everything from a file with the
		// provided dir and put the data here

	}

	/**
	 * <p>
	 * Add provided attribute to the file. The format for the file is
	 * AttributeName,isWeighted,AttributeType.
	 * </p>
	 * 
	 * @param attr
	 *            the attribute to be added
	 */
	public void addAttribute(Attribute attr) {

		// TODO: Finish the method, append a new line with provided attribute in
		// the correct format in the file with the provided dir

	}

	public Attribute getAttribute(String name) {
		if (attributes.containsKey(name))
			return attributes.get(name);
		else
			throw new IllegalArgumentException(String.format("%s is not an attribute", name));
	}

	public HashMap<String, Attribute> getAllAttributes() {
		return attributes;
	}

	public LinkedList<String> getAllNames() {
		return attributeName;
	}

}
