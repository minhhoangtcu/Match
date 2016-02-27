package testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.simulator.PeopleGenerator;

public class TestCompare {
	
	private static final int PEOPLE = 10000;

	public TestCompare() throws FileNotFoundException, IOException {
		
		String attrURL = "data/attributes.match";
		
		PeopleGenerator random = new PeopleGenerator(attrURL);
		
		System.out.printf(">> GENERATE PEOPLE INTO A FILE \n");
		String data = "data/testData.csv";
		String fa = "data/testFa.csv";
		random.generatePeopleToFiles(data, fa, PEOPLE);
		System.out.printf(">> GENERATE 10 PEOPLE INTO A FILE TESTING ENDED\n\n");
		
	}
	
}
