package io.match.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class AttributesIO {

	private String dirAttr;
	private LinkedList<Attribute> attributes;
	
	private static final String IGNORE = "ignore";
	private static final String GENERAL = "general";
	private static final String WEIGHTED = "weighted";
	private static final String MULTIPLE = "multiple";
	private static final String SCALE = "scale";
	
	private boolean isDebug;

	public AttributesIO(String dir) throws FileNotFoundException, IOException {
		this.dirAttr = dir;
		attributes = new LinkedList<>();
		isDebug = false;
	}

	/**
	 * <p>
	 * Read all attributes from the file and put them into both the linked list
	 * and the hash map
	 * <p>
	 * 
	 * @throws IOException
	 */
	public void readAttributes() throws FileNotFoundException, IOException {
		
		BufferedReader bf = new BufferedReader(new FileReader(dirAttr));

		String line = "undefined";
		try {
			while ((line = bf.readLine()) != null) {
				String[] elements = line.split(",");
				String name = elements[0];

				attributes.add(getAttributeOfType(name, elements, 1));
			}
			
			if (isDebug)
				System.out.printf("Successfully read all attributes from %s\n\n", dirAttr);
			
		} catch (Exception e) {
			throw new IOException(String.format("Attributes in file %s is corrupted.\nProgram failed on line: %s\n%s", dirAttr, line, e.getMessage()));
		} finally {
			bf.close();
		}
		
	}
	
	/**
	 * Add an attribute to the linked list in this class
	 * 
	 * @param name the name of the attribute
	 * @param elements all elements read from the line
	 * @param typeIndex the index where the type of the attribute is in the array 
	 * @throws Exception 
	 */
	private Attribute getAttributeOfType(String name, String[] elements, int typeIndex) {
		
		switch (elements[typeIndex++]) {
		
		case IGNORE:
			return getAttributeOfType(name, elements, typeIndex).setIgnored(true);
			
		case GENERAL:
			if (isDebug)
				System.out.printf("Added general attribute %s\n", name);
			
			return new GeneralAttribute(name);
			
		case WEIGHTED:
			switch (elements[typeIndex++]) {
			
			case MULTIPLE:
				int numberOfChoices = Integer.parseInt(elements[typeIndex++]);
				OneToMultipleAttribute temp = new OneToMultipleAttribute(name);
				for (int i = 0; i < numberOfChoices; i++) {
					temp.addPossibleChoice(elements[typeIndex+i]);
				}
				
				if (isDebug)
					System.out.printf("Added multiple attribute %s\n", name);
				
				return temp;
				
			case SCALE:
				int from = Integer.parseInt(elements[typeIndex++]);
				int to = Integer.parseInt(elements[typeIndex]);
				
				if (isDebug)
					System.out.printf("Added scale attribute %s\n", name);
				
				return new ScaleAttribute(name).setFrom(from).setTo(to); 
				
			default:
				throw new IllegalArgumentException("Case not accepted, must be scale or multiple");
			}
			
		default:
			throw new IllegalArgumentException("Case not accepted, must be ignore, general, or weighted");
		}
	}

	/**
	 * <p>
	 * Add provided attribute to the file. The format for the file is
	 * AttributeName,isWeighted,AttributeType.
	 * </p>
	 * 
	 * @param attr
	 *            the attribute to be added
	 * @throws IOException 
	 */
	public void addAttribute(Attribute attr) throws IOException {

		if (isDebug)
			System.out.printf("Adding to attribute list: %s\n", attr.getAttributeName());
		
		BufferedWriter bf = new BufferedWriter(new FileWriter(dirAttr, true));
		
		bf.write(attr.getAttributeName());
		bf.write(",");
		if (attr.isIgnored()) {
			bf.write(IGNORE);
			bf.write(",");
		}
		
		switch (attr.getAttributeType()) {
		case GENERAL:
			bf.write(GENERAL);
			break;
			
		case WEIGHTED_ONE_TO_MULTIPLE:
			OneToMultipleAttribute tempM = (OneToMultipleAttribute) attr;
			bf.write(String.format("%s,%s,", WEIGHTED, MULTIPLE));
			
			int numPossibleChoices = tempM.getNumOfPossibleChoices();
			String[] choices = tempM.getPossibleChoices();
			for (int i = 0; i < numPossibleChoices; i++) {
				bf.write(choices[i]);
				if (i != numPossibleChoices-1)
					bf.write(",");
			}
			break;
			
		case WEIGHTED_SCALE:
			ScaleAttribute tempS = (ScaleAttribute) attr;
			bf.write(String.format("%s,%s,%d,%d", WEIGHTED, SCALE, tempS.getFrom(), tempS.getTo()));
			break;
		}
		
		bf.write('\n');
		bf.close();
	}

	public LinkedList<Attribute> getAttributes() {
		return attributes;
	}
	
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
}
