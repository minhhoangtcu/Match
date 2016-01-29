package application;

import java.util.HashMap;
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
	private final static String ATTRIBUTE_DIR = "tobeornottobe";
	private String peopleDir;
	
	// Init IO
	private AttributesIO attributeIO;
	private PeopleIO peopleIO;
	
	public Model() {
		
		students = new LinkedList<>();
		faculties = new LinkedList<>();
		attributeIO = new AttributesIO(ATTRIBUTE_DIR);
		peopleIO = new PeopleIO(peopleDir);
		
	}
	
	public LinkedList<Person> getStudents() {
		return students;
	}
	public LinkedList<Person> getFaculties() {
		return faculties;
	}
	public LinkedList<Attribute> getAttributes() {
		return attributeIO.getAttributes();
	}
}
