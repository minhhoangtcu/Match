package testing;

import java.io.IOException;
import io.match.algorithm.Compare;
import io.match.datastructure.Person;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;
import io.match.reader.PeopleStringReader;

public class TestIO {

	public static void main(String[] args) throws Exception {
		TestIO test = new TestIO();
	}

	public TestIO() throws Exception {

		String attrURL = "data/attributes.match";
		String peopleURL = "data/data.csv";
		String faURL = "data/studentsFA.csv";
		
		AttributesIO aIO = new AttributesIO(attrURL);
		aIO.readAttributes();
		PeopleIO pIO = new PeopleIO(peopleURL, faURL, aIO.getAttributes());
		pIO.readPeople();
		
		testMatching(aIO, pIO);
		testPrintingAllPeople(pIO);
		
		testAdding(pIO);
		
	}
	
	public static void testMatching(AttributesIO aIO, PeopleIO pIO) throws Exception {
		System.out.printf(">> COMPARE TESTING\n");

		for (Person iPerson : pIO.getPeople()) {

			for (Person jPerson : pIO.getPeople()) {
				if (iPerson == jPerson)
					continue;

				String nameI = iPerson.getName();
				String nameJ = jPerson.getName();

				double match = Compare.getMatch(iPerson, jPerson);

				System.out.printf("%s and %s \t\t\t\t%.2f\n", nameI, nameJ, match);
			}

		}

		System.out.printf(">> COMPARE TESTING ENDED\n\n");
	}

	public static void testPrintingAllPeople(PeopleIO pIO) {
		System.out.printf(">> PRINTING ALL TESTING\n");
		
		for (Person person: pIO.getPeople()) {
			PeopleStringReader.print(person);
			System.out.println();
		}
		
		System.out.printf(">> PRINTING ALL TESTING ENDED\n\n");
	}
	
	public static void testAdding(PeopleIO pIO) throws IOException {
		System.out.printf(">> ADDING PEOPLE TESTING\n");
		
		for (int i = 0; i < 5; i++) {
			Person random = pIO.getPeople().get((int) (Math.random()*pIO.getPeople().size()));
			pIO.addPerson(random);
		}
		
		System.out.printf(">> ADDING PEOPLE TESTING ENDED\n\n");
	}
}
