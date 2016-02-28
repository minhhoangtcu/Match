package testing;

import java.io.BufferedWriter;
import java.io.FileWriter;

import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;
import io.match.simulator.PeopleGenerator;

public class TestCompare {
	
	private static final int LOOP = 50;
	private static final int STEP = 100;
	
	String data = "data/testData.csv";
	String fa = "data/testFa.csv";
	String attrURL = "data/attributes.match";

	public static void main(String[] args) throws Exception {
		new TestCompare();
	}
	
	public TestCompare() throws Exception {
		
		BufferedWriter bf = new BufferedWriter(new FileWriter("data/testCompare.csv"));
		
		int numOfPeople = 0;
		for (int i = 0; i < LOOP; i++) {
			numOfPeople += STEP;
			bf.write(String.format("%d,%d\n", getTimeTest(numOfPeople), numOfPeople));
		}
		bf.close();
		System.out.println("Finished Testing Compare!");
	}
	
	public long getTimeTest(int numberOfPeople) throws Exception {
		PeopleGenerator random = new PeopleGenerator(attrURL);
		random.generatePeopleToFiles(data, fa, numberOfPeople);
		AttributesIO aIO = new AttributesIO(attrURL);
		aIO.readAttributes();
		PeopleIO pIO = new PeopleIO(data, fa, aIO.getAttributes());
		pIO.readPeople();
		
		long tStart = System.currentTimeMillis();
		TestIO.testMatchingWithoutPrinting(pIO);
		
		return System.currentTimeMillis() - tStart;
	}
	
}
