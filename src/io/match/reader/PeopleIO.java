package io.match.reader;

import java.util.LinkedList;

import io.match.datastructure.Person;

public class PeopleIO {

	private String dir;
	private LinkedList<Person> people;

	public PeopleIO(String dir) {
		this.dir = dir;
		people = new LinkedList<>();
		initPeople();
	}

	/**
	 * <p>
	 * Read all people from the file and put them into the linked list data
	 * structure
	 * <p>
	 */
	private void initPeople() {

		// TODO: Read data from a .csv file and put the data here. Refer to
		// /data/testform1.csv for the structure. Use the LinkedList of
		// attributes from AttributesIO to construct a person and add attributes
		// to them dynamically

	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public LinkedList<Person> getPeople() {
		return people;
	}
}
