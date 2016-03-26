package io.match.simulator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataGenerator {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		System.out.println("<<< BEGIN GENERATING >>>");
		
		String attrURL = "data/attributes.match";
		PeopleGenerator random = new PeopleGenerator(attrURL);
		
		int numOfFiles = 10;
		int numOfSkips = 10;
		int start = 10;
		
		for (int num = start; num < numOfFiles * numOfSkips; num += numOfSkips) {
			String urlStudentsData = String.format("data/testStudents%dData.csv", num);
			String urlStudentsFA = String.format("data/testStudents%dFA.csv", num);
			
			String urlFalcutiesData = String.format("data/testFalcuties%dData.csv", num);
			String urlFalcutiesFA = String.format("data/testFalcuties%dFA.csv", num);
			
			random.generatePeopleToFiles(urlStudentsData, urlStudentsFA, num);
			random.generatePeopleToFiles(urlFalcutiesData, urlFalcutiesFA, num);
			System.out.printf("Generated files for %d people\n", num);
		}
		
		System.out.println("<<< FINISHED GENERATING >>>");
		
	}
}
