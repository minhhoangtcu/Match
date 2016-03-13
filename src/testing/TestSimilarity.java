package testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.Model;
import io.match.algorithm.Compare;
import io.match.algorithm.Similarity;
import io.match.datastructure.Person;
import io.match.reader.PeopleStringReader;

public class TestSimilarity {
	
	private static final int TOP = 3;
	
	public static void main(String[] args) {
		new TestSimilarity();
	}
	
	public TestSimilarity() {
		Model m;
		
		try {
			m = new Model();
			
			for (Person first: m.getStudents()) {
				for (Person second: m.getFaculties()) {
					System.out.printf("%s and %s\n", first.getName(), second.getName());
					for (Similarity sim: Compare.getTopSimilarities(first, second, TOP)) {
						System.out.printf("%s %f\n", sim.getAttributeName(), sim.getPointAverage());
					}
					System.out.println();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
