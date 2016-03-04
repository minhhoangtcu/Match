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
	
	
	public LinkedList<Person> getStudents() {
		return students;
	}
	
	public boolean isEmptyStudents() {
		return students.size() == 0;
	}
	
	public LinkedList<Person> getFaculties() {
		return faculties;
	}
	
	public boolean isEmptyFaculties() {
		return faculties.size() == 0;
	}
	
	public LinkedList<Attribute> getAttributes() {
		return attributeIO.getAttributes();
	}
}
