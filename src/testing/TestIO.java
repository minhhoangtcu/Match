package testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.datastructure.Person;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;
import io.match.reader.PeopleStringReader;

public class TestIO {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		TestIO test = new TestIO();
	}
	
	public TestIO() throws FileNotFoundException, IOException {
		
		
		String attrURL = "data/attributes.match";
		String peopleURL = "data/testform1.csv";
		
		AttributesIO aIO = new AttributesIO(attrURL);
		PeopleIO pIO = new PeopleIO(peopleURL, aIO.getAttributes());
		
		System.out.printf(">> IO TESTING\n");
		
		for (Person one : pIO.getPeople()) {
			PeopleStringReader.print(one);
		}
		
		System.out.printf(">> IO TESTING ENDED\n\n");
	}
}
