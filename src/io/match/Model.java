package io.match;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;

public class Model {
	
	// Define basic data structure
	private LinkedList<Person> students;
	private LinkedList<Person> faculties;
	
	// Link to the files for the IO
	private final static String ATTRIBUTE_DIR = "data/attributes.match";
	
	// Used for debugging purposes
	private boolean isDebug = true;
	private String studentsDir = "data/data.csv";
	private String falcutyDir = "data/data2.csv";
	private String studentsFADir = "data/studentsFA.csv";
	private String falcutyFADir = "data/falcutiesFA.csv";
	
	// Init IO
	private AttributesIO attributeIO;
	private PeopleIO studentsIO, falcutiesIO;
	
	public Model() throws FileNotFoundException, IOException {
		students = new LinkedList<>();
		faculties = new LinkedList<>();
		
		attributeIO = new AttributesIO(ATTRIBUTE_DIR);
		attributeIO.setDebug(isDebug);
		attributeIO.readAttributes();
		
		if (isDebug) {
			loadStudents(studentsDir, studentsFADir);
			loadFalcuty(falcutyDir, falcutyFADir);
		}
		
	}
	
	public void loadStudents(String studentsDir, String studentsFADir) throws FileNotFoundException, IOException {
		this.studentsDir = studentsDir;
		this.studentsFADir = studentsFADir;
		studentsIO = new PeopleIO(studentsDir, studentsFADir, getAttributes());
		studentsIO.setDebug(isDebug);
		studentsIO.readPeople();
		students = studentsIO.getPeople();
	}
	
	public void loadFalcuty(String falcutyDir, String falcutyFADir) throws FileNotFoundException, IOException {
		this.falcutyDir = falcutyDir;
		this.falcutyFADir = falcutyFADir;
		falcutiesIO = new PeopleIO(falcutyDir, falcutyFADir, getAttributes());
		falcutiesIO.setDebug(isDebug);
		falcutiesIO.readPeople();
		faculties = falcutiesIO.getPeople();
	}
	
	/*
	 * STUDENTS' METHODS 
	 */
	
	public Person removeStudent(String name) {
		for (Person person : students) {
			if (person.getName().equals(name)) {
				students.remove(person);
				return person;
			}
		}
		throw new IllegalArgumentException(String.format("The student with provided name: %s does not exist in the data base", name));
	}
	
	public LinkedList<Person> getStudents() {
		return students;
	}
	
	/**
	 * Get the student with the provided name.
	 * 
	 * @param name distinct name of the student to search for in the database
	 * @return the person with matching provided name
	 */
	public Person getStudent(String name) {
		try {
			return getPerson(students, name);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("The student with provided name: %s does not exist in the data base", name));
		}
	}
	
	public boolean isEmptyStudents() {
		return students.size() == 0;
	}

	/*
	 * FALCUTIES' METHODS 
	 */

	public Person removeFalcuty(String name) {
		for (Person person : faculties) {
			if (person.getName().equals(name)) {
				faculties.remove(person);
				return person;
			}
		}
		throw new IllegalArgumentException(String.format("The faculty with provided name: %s does not exist in the data base", name));
	}
	
	/**
	 * Get the faculty with the provided name.
	 * 
	 * @param name distinct name of the faculty to search for in the database
	 * @return the person with matching provided name
	 */
	public Person getFaculty(String name) {
		try {
			return getPerson(faculties, name);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("The faculty with provided name: %s does not exist in the data base", name));
		}
	}
	
	public LinkedList<Person> getFaculties() {
		return faculties;
	}
	
	public boolean isEmptyFaculties() {
		return faculties.size() == 0;
	}
	
	/*
	 * OTHER HELPER METHOD
	 */
	
	public LinkedList<Attribute> getAttributes() {
		return attributeIO.getAttributes();
	}
	
	private Person getPerson(LinkedList<Person> people, String name) {
		for (Person person : people) {
			if (person.getName().equals(name))
				return person;
		}
		throw new IllegalArgumentException();
	}
	
	
}
