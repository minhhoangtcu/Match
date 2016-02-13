package io.match.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class PeopleIO {

	private String dir;
	private LinkedList<Person> people;
	private LinkedList<Attribute> attributes;

	private static final String VERY_IMPORTANT = "Very important";
	private static final String SOMEWHAT_IMPORTANT = "Somewhat important";
	private static final String NOT_IMPORTANT = "Not important";

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
					String data = getData(elements[index++]); // TODO: implement
																// a way to
					// handle other data
					// types

					switch (attribute.getAttributeType()) {

					case GENERAL:
						temp.addGeneralAttribute(name, data);
						break;

					case WEIGHTED_ONE_TO_MULTIPLE:
						// If we are putting a weighted attribute in, we also
						// know that the next 2 fields are: 1. expecting same
						// and 2. importance
						temp.addOneToMultipleAttribute(name,
								getData(data),
								getData(elements[index++]).split(";"),
								readInterest(getData(elements[index++])));
						break;

					case WEIGHTED_SCALE:
						ScaleAttribute convertedAttribute = (ScaleAttribute) attribute;
						temp.addScaleAttribute(name, 
								convertedAttribute.getFrom(), 
								convertedAttribute.getTo(),
								Integer.parseInt(getData(data)), 
								Integer.parseInt(getData(elements[index++])),
								readInterest(getData(elements[index++])));
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
		switch (input) {
		case NOT_IMPORTANT:
			return Interest.NOT_IMPORTANT;
		case SOMEWHAT_IMPORTANT:
			return Interest.SOMEWHAT_IMPORTANT;
		case VERY_IMPORTANT:
			return Interest.VERY_IMPORTANT;
		}
		throw new Exception(String.format("Importance: %s is not %s, %s or %s\n", input, NOT_IMPORTANT,
				SOMEWHAT_IMPORTANT, VERY_IMPORTANT));
	}

	private String getData(String data) {
		return data.replaceAll("\"", "");
	}

	public LinkedList<Person> getPeople() {
		return people;
	}
}
