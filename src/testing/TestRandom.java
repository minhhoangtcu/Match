package testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.datastructure.Person;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;
import io.match.reader.PeopleStringReader;
import io.match.simulator.PeopleGenerator;

public class TestRandom {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		new TestRandom();
		
	}
	
	public TestRandom() throws FileNotFoundException, IOException {
		String attrURL = "data/attributes.match";
		
		PeopleGenerator random = new PeopleGenerator(attrURL);
		System.out.printf(">> GENERATE 10 PEOPLE \n");
		for (Person person: random.generatePeople(10)) {
			PeopleStringReader.print(person);
		}
		System.out.printf(">> GENERATE 10 PEOPLE TESTING ENDED\n\n");
		
		int num = 100;
	
		System.out.printf(">> GENERATE PEOPLE INTO A FILE \n");
		String data = "data/testData.csv";
		String fa = "data/testFa.csv";
		random.generatePeopleToFiles(data, fa, num);
		System.out.printf(">> GENERATE PEOPLE INTO A FILE TESTING ENDED\n\n");
		
		
		System.out.printf(">> READ THE GENERATED PEOPLE \n");
		AttributesIO aIO = new AttributesIO(attrURL);
		aIO.readAttributes();
		PeopleIO pIO = new PeopleIO(data, fa, aIO.getAttributes());
		pIO.readPeople();
		TestIO.testPrintingAllPeople(pIO);
		System.out.printf(">> READ THE GENERATED PEOPLE TESTING ENDED \n");
	}

}
