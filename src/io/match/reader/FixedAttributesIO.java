package io.match.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import io.match.datastructure.Person;

public class FixedAttributesIO {

	private String dirAttr;
	private HashMap<String, FixedAttribute> attributes;
	private boolean isDebug;
	private static final String HEADER = "Name,Matched with,Number of avaiable Matches\n";

	public FixedAttributesIO(String dir) {
		this.dirAttr = dir;
		attributes = new HashMap<>();
		isDebug = false;
	}
	
	public void readAttributes() throws FileNotFoundException, IOException {
		
		if (isDebug)
			System.out.println("Init list of FIXED attributes");
		
		BufferedReader bf = new BufferedReader(new FileReader(dirAttr));
		
		String line = "undefined";
		try {
			bf.readLine(); // Ignore the first line
			while ((line = bf.readLine()) != null) {
				String[] elements = line.split(",");
				FixedAttribute temp;
				
				String name = IOUtil.getData(elements[0]);
				int numMatchesAvaiable = Integer.parseInt(IOUtil.getData(elements[2]));
				temp = new FixedAttribute(numMatchesAvaiable);
					
				for (String match: IOUtil.getData(elements[1]).split(";")) {
					if (!match.equals(""))
						temp.addMatch(match);
				}
					
				attributes.put(name, temp);
			}
			
			if (isDebug)
				System.out.printf("Succesfully read all fixed attributes from file %s\n\n", dirAttr);
			
		} catch (Exception e) {
			throw new IOException(String.format("FIXED Attributes in file %s is corrupted.\nProgram failed on line: %s\n%s", dirAttr, line, e.getMessage()));
		} finally {
			bf.close();
		}
	}
	
	public void addHeaderToFile() throws IOException {
		
		if (isDebug)
			System.out.printf("Adding header\n");
		
		BufferedWriter bf = new BufferedWriter(new FileWriter(dirAttr, true));
		bf.write(HEADER);
		bf.close();
		
	}
	
	public void addAttribute(Person person) throws IOException {
		
		if (isDebug)
			System.out.printf("Adding to FA: %s\n", person.getName());
		
		BufferedWriter bf = new BufferedWriter(new FileWriter(dirAttr, true));
		
		bf.write(person.getName());
		bf.write(",");
		
		int numOfMatches = person.getMatches().size();
		String[] matches = person.getMatches().toArray(new String[numOfMatches]);
		for (int i = 0; i < numOfMatches; i++) {
			bf.write(matches[i]);
			if (i != numOfMatches - 1)
				bf.write(";");
		}
		bf.write(",");
		
		bf.write(person.getNumMatchesAvaiable() + "");
		
		bf.write('\n');
		
		bf.close();
	}

	public HashMap<String, FixedAttribute> getAttributes() {
		return attributes;
	}
	
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
}
