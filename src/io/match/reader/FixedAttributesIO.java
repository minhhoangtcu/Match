package io.match.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class FixedAttributesIO {

	private String dir;
	private HashMap<String, FixedAttribute> attributes;

	public FixedAttributesIO(String dir) throws FileNotFoundException, IOException {
		this.dir = dir;
		attributes = new HashMap<>();
		initAttribute();
	}
	
	private void initAttribute() throws FileNotFoundException, IOException {
		
		System.out.println("Init list of FIXED attributes");
		BufferedReader bf = new BufferedReader(new FileReader(dir));
		
		String line = "undefined";
		try {
			bf.readLine(); // Ignore the first line
			while ((line = bf.readLine()) != null) {
				String[] elements = line.split(",");
				FixedAttribute temp;
				
				String name = IOUtil.getData(elements[0]);
				boolean isMatch = IOUtil.getData(elements[1]).toLowerCase().equals("true") ? true : false;
				
				if (elements.length == 5) {
					int numMatched = Integer.parseInt(IOUtil.getData(elements[3]));
					int numMatchesAvaiable = Integer.parseInt(IOUtil.getData(elements[4]));
					temp = new FixedAttribute(isMatch, numMatchesAvaiable, numMatched);
					
					for (String match: IOUtil.getData(elements[2]).split(";")) {
						temp.addMatch(match);
					}
					
				} else {
					temp = new FixedAttribute(isMatch, 1, isMatch ? 1 : 0);
				}
				
				attributes.put(name, temp);
			}
			
			System.out.printf("End of file %s\n\n", dir);
		} catch (Exception e) {
			throw new IOException(String.format("FIXED Attributes in file %s is corrupted.\nProgram failed on line: %s\n%s", dir, line, e.getMessage()));
		} finally {
			bf.close();
		}
	}

	public HashMap<String, FixedAttribute> getAttributes() {
		return attributes;
	}

}

/**
 * We load the fixed attribute file individually; thus, we need to create a
 * class to hold all the data before assigning them into people
 */
class FixedAttribute {

	private int numMatchesAvaiable, numMatched;
	private boolean isMatched;
	private LinkedList<String> matches;

	public FixedAttribute(boolean isMatched, int numMatchesAvaiable, int numMatched) {
		matches = new LinkedList<>();
		this.isMatched = isMatched;
		this.numMatchesAvaiable= numMatchesAvaiable;
		this.numMatched = numMatched;
	}
	
	public void addMatch(String match) {
		matches.add(match);
	}
	public int getNumMatchesAvaiable() {
		return numMatchesAvaiable;
	}
	public int getNumMatched() {
		return numMatched;
	}
	public boolean isMatched() {
		return isMatched;
	}
	public LinkedList<String> getMatches() {
		return matches;
	}

}
