package io.match.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.Attribute.Interest;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.MultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class PeopleIO {

	private String dir;
	private LinkedList<Person> people;
	private LinkedList<Attribute> attributes;

	public PeopleIO(String dir, LinkedList<Attribute> attributes) throws FileNotFoundException, IOException {
		this.dir = dir;
		this.attributes = attributes;
		people = new LinkedList<>();
		initPeople();
	}

	/**
	 * <p>
	 * Read all people from the file and put them into the linked list data
	 * structure
	 * <p>
	 * 
	 * @throws FileNotFoundException,
	 *             IOException
	 */
	private void initPeople() throws FileNotFoundException, IOException {

		System.out.printf("Init list of people\n");
		BufferedReader bf = new BufferedReader(new FileReader(dir));

		String line = "undefined";
		try {
			bf.readLine(); // Ignore the first line;
			while ((line = bf.readLine()) != null) {

				String[] elements = line.split(",");
				Person temp = new Person();
				people.add(temp);

				int index = 0;
				for (Attribute attribute : attributes) {
					String name = attribute.getAttributeName();
					String data = elements[index++]; // TODO: implement a way to
														// handle other data
														// types

					switch (attribute.getAttributeType()) {

					case GENERAL:
						temp.addAttribute(new GeneralAttribute(name, data));
						break;

					case WEIGHTED_MULTIPLE:
						// If we are putting a weighted attribute in, we also
						// know that the next 2 fields are: 1. expecting same
						// and 2. importance

						MultipleAttribute tempAttr1 = new MultipleAttribute(name);
						tempAttr1.setChoice(data);
						tempAttr1.setExpecting(readExpecting(elements[index++]));
						tempAttr1.setInterst(readInterest(elements[index++]));
						temp.addAttribute(tempAttr1);
						break;

					case WEIGHTED_SCALE:
						ScaleAttribute convertedAttribute = (ScaleAttribute) attribute;
						ScaleAttribute tempAttr2 = new ScaleAttribute(name).setFrom(convertedAttribute.getFrom())
								.setTo(convertedAttribute.getTo());
						tempAttr2.setChoice(Integer.parseInt(data));
						tempAttr2.setExpecting(readExpecting(elements[index++]));
						tempAttr2.setInterst(readInterest(elements[index++]));
						temp.addAttribute(tempAttr2);
						break;
					case IGNORE:
						break;
					default:
						throw new Exception("Attribute is not initialized");

					}
				}
			}
			
			System.out.printf("End of file %s\n\n", dir);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(String.format("Person in file %s is corrupted.\nProgram failed on line: %s\n%s", dir,
					line, e.getMessage()));
		} finally {
			bf.close();
		}

	}

	private Interest readInterest(String input) throws Exception {
		int value = Integer.parseInt(input);
		switch (value) {
		case 1:
			return Interest.NOT_IMPORTANT;
		case 2:
			return Interest.SOMEWHAT_IMPORTANT;
		case 3:
			return Interest.NOT_IMPORTANT;
		}
		throw new Exception(String.format("Importance: %s is not 1, 2 or 3\n", input));
	}

	private boolean readExpecting(String input) throws Exception {
		if (input.equals("true"))
			return true;
		else if (input.equals("false"))
			return false;
		else
			throw new Exception(String.format("Expecting: %s is not true or false\n", input));
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public LinkedList<Person> getPeople() {
		return people;
	}
}
