package testing;

import io.match.algorithm.Compare;
import io.match.datastructure.Person;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;

public class TestIO {

	public static void main(String[] args) throws Exception {
		TestIO test = new TestIO();
	}

	public TestIO() throws Exception {

		String attrURL = "data/attributes.match";
		String peopleURL = "data/testform1.csv";

		AttributesIO aIO = new AttributesIO(attrURL);
		PeopleIO pIO = new PeopleIO(peopleURL, aIO.getAttributes());

		System.out.printf(">> IO TESTING\n");

		for (Person iPerson : pIO.getPeople()) {

			for (Person jPerson : pIO.getPeople()) {
				if (iPerson == jPerson)
					continue;

				String nameI = iPerson.getGeneralAttribute("name");
				String nameJ = jPerson.getGeneralAttribute("name");

				double match = Compare.getMatch(iPerson, jPerson);

				System.out.printf("%s and %s \t\t%.2f\n", nameI, nameJ, match);
			}

		}

		System.out.printf(">> IO TESTING ENDED\n\n");
	}
}
