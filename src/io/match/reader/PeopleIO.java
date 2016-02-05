package io.match.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
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
	 * @throws FileNotFoundException, IOException 
	 */
	private void initPeople() throws FileNotFoundException, IOException {

		BufferedReader bf = new BufferedReader(new FileReader(dir));
		
		String line = "undefined";
		try {
			bf.readLine(); // Ignore the first line;
			while ((line = bf.readLine()) != null) {
				
				String[] elements = line.split(",");
				Person temp = new Person();
				
				int index = 0;
				for (Attribute attribute : attributes) {
					String name = attribute.getAttributeName();
					String data = elements[index++]; // TODO: implement a way to handle other data types
					
					switch (attribute.getWeight()) {
					
					case GENERAL:
						temp.addAttribute(new GeneralAttribute(name, data));
						break;
					
					case WEIGHTED:
						switch (attribute.getAttributeType()) {
						
						case MULTIPLE:
							MultipleAttribute tempAttr1 = new MultipleAttribute(name);
							tempAttr1.setChoice(data);
							temp.addAttribute(tempAttr1);
							break;
							
						case SCALE:
							ScaleAttribute convertedAttribute = (ScaleAttribute) attribute;
							ScaleAttribute tempAttr2 = new ScaleAttribute(name).setFrom(convertedAttribute.getFrom()).setTo(convertedAttribute.getTo());
							tempAttr2.setChoice(Integer.parseInt(data));
							temp.addAttribute(tempAttr2);
							break;
							
						}
						break;
						
					case IGNORE:
						break;
					
					}
					
				}
				
				people.add(temp);
			}
		}
		catch (Exception e) {
			throw new IOException(String.format("Person in file %s is corrupted.\nProgram failed on line: %s", dir, line));
		}
		finally {
			bf.close();
		}
		
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public LinkedList<Person> getPeople() {
		return people;
	}
}
