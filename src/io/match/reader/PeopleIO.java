package io.match.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class PeopleIO {

	private String dirPeople;
	private HashMap<String, FixedAttribute> fixedAttributes;
	private LinkedList<Person> people;
	private LinkedList<Attribute> attributes;

	/**
	 * <p>
	 * Read all the data from text files and load it into the program.
	 * </p>
	 * 
	 * @param dirPeople
	 *            the directory to the people
	 * @param fixedAttributes
	 *            the hash map of a name of a person to the machine generated
	 *            content such as his matches, number of matches, and avaiable
	 *            matches.
	 * @param attributes
	 *            the linked list of attributes. As the program reads through
	 *            the data text file, it is going to look for these attributes.
	 * @throws FileNotFoundException
	 *             cannot find the file
	 * @throws IOException
	 *             cannot read from the file, or file is corrupted
	 */
	public PeopleIO(String dirPeople, HashMap<String, FixedAttribute> fixedAttributes, LinkedList<Attribute> attributes)
			throws FileNotFoundException, IOException {
		this.dirPeople = dirPeople;
		this.fixedAttributes = fixedAttributes;
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
		BufferedReader bf = new BufferedReader(new FileReader(dirPeople));

		String line = "undefined";
		try {
			bf.readLine(); // Ignore the first line;
			while ((line = bf.readLine()) != null) {

				String[] elements = line.split(",");
				int index = 0;

				String personName = IOUtil.getData(elements[index++]); // Name
																		// must
																		// always
																		// be
																		// the
																		// first
																		// element,
																		// because
																		// it
																		// will
																		// act
																		// as an
																		// identifier.
				Person temp = new Person(personName);
				people.add(temp);

				for (Attribute attribute : attributes) {

					String name = attribute.getAttributeName();
					String data = IOUtil.getData(elements[index++]);

					switch (attribute.getAttributeType()) {

					case GENERAL:
						temp.addGeneralAttribute(name, data);
						break;

					case WEIGHTED_ONE_TO_MULTIPLE:
						// If we are putting a weighted attribute in, we also
						// know that the next 2 fields are: 1. expecting same
						// and 2. importance
						temp.addOneToMultipleAttribute(name, IOUtil.getData(data),
								IOUtil.getData(elements[index++]).split(";"),
								IOUtil.readInterest(IOUtil.getData(elements[index++])));
						break;

					case WEIGHTED_SCALE:
						ScaleAttribute convertedAttribute = (ScaleAttribute) attribute;
						temp.addScaleAttribute(name, convertedAttribute.getFrom(), convertedAttribute.getTo(),
								Integer.parseInt(IOUtil.getData(data)),
								Integer.parseInt(IOUtil.getData(elements[index++])),
								IOUtil.readInterest(IOUtil.getData(elements[index++])));
						break;

					default:
						throw new Exception("Attribute is not initialized");
					}
				}

				FixedAttribute tempFA = fixedAttributes.get(personName);
				temp.setMatched(tempFA.isMatched());
				temp.setNumMatched(tempFA.getNumMatched());
				temp.setNumMatchesAvaiable(tempFA.getNumMatchesAvaiable());
				temp.setAllMatch(tempFA.getMatches());

			}
			System.out.printf("End of file %s\n\n", dirPeople);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(String.format("Person in file %s is corrupted.\nProgram failed on line: %s\n%s",
					dirPeople, line, e.getMessage()));
		} finally {
			bf.close();
		}

	}

	public void addPerson(Person person) throws IOException {

		System.out.printf("Adding to data: %s\n", person.getName());
		BufferedWriter bf = new BufferedWriter(new FileWriter(dirPeople, true));

		bf.write(person.getName() + ",");
		int numOfAttributes = person.getAttributes().size();
		Attribute[] attributes = person.getAttributes().toArray(new Attribute[numOfAttributes]);

		for (int i = 0; i < numOfAttributes; i++) {

			switch (attributes[i].getAttributeType()) {
			case GENERAL:
				bf.write(PeopleStringReader.getDataGeneral(attributes[i]));
				break;
			case WEIGHTED_ONE_TO_MULTIPLE:
				bf.write(PeopleStringReader.getDataOneToMultiple(attributes[i]));
				bf.write(",");

				int numOfExpectations = PeopleStringReader.getExpectingOneToMultiple(attributes[i]).size();
				String[] expecting = PeopleStringReader.getExpectingOneToMultiple(attributes[i])
						.toArray(new String[numOfExpectations]);
				for (int j = 0; j < expecting.length - 1; j++) {
					bf.write(expecting[j]);
					bf.write(";");
				}
				bf.write(expecting[expecting.length - 1]);
				bf.write(",");

				bf.write(PeopleStringReader.getImportanceOneToMultiple(attributes[i]));
				break;

			case WEIGHTED_SCALE:
				bf.write(PeopleStringReader.getDataScale(attributes[i]) + "");
				bf.write(",");

				bf.write(PeopleStringReader.getExpectingScale(attributes[i]) + "");
				bf.write(",");

				bf.write(PeopleStringReader.getImportanceScale(attributes[i]));
				break;
			}

			if (i != numOfAttributes - 1)
				bf.write(",");

		}
		bf.write('\n');

		bf.close();

	}

	public LinkedList<Person> getPeople() {
		return people;
	}
}
