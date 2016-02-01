package testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;

public class TestIO {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		TestIO test = new TestIO();
	}
	
	public TestIO() throws FileNotFoundException, IOException {
		
		String attrURL = "data/attributes.match";
		String peopleURL = "data/testform1.csv";
		
		AttributesIO aIO = new AttributesIO(attrURL);
		PeopleIO pIO = new PeopleIO(peopleURL, aIO.getAttributes());
		
		for (Person one : pIO.getPeople()) {
			
			for (Attribute two : one.getAttributes()) {
				System.out.println(two.getAttributeName());
			}
			
		}
		
	}

}
