package application;

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
	private final static String ATTRIBUTE_DIR = "tobeornottobe";
	private String peopleDir = "data/testform1.csv"; // This can still be changed
	
	// Init IO
	private AttributesIO attributeIO;
	private PeopleIO peopleIO;
	
	public Model() throws FileNotFoundException, IOException {
		students = new LinkedList<>();
		faculties = new LinkedList<>();
		
		attributeIO = new AttributesIO(ATTRIBUTE_DIR);
		peopleIO = new PeopleIO(peopleDir, attributeIO.getAttributes());
		
		// TODO: Load from the view
		students = peopleIO.getPeople();
	}
	
	public LinkedList<Person> getStudents() {
		return students;
	}
	
	private LinkedList<Person> getFaculties() {
		return faculties;
	}
	public LinkedList<Attribute> getAttributes() throws IOException {
		return attributeIO.getAttributes();
	}
}
