package io.match.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class AttributesIO {

	private String dir;
	private LinkedList<Attribute> attributes;
	
	private static final String IGNORE = "ignore";
	private static final String GENERAL = "general";
	private static final String WEIGHTED = "weighted";
	private static final String MULTIPLE = "multiple";
	private static final String SCALE = "scale";

	public AttributesIO(String dir) throws FileNotFoundException, IOException {
		this.dir = dir;
		attributes = new LinkedList<>();
		initAttribute();
	}

	/**
	 * <p>
	 * Read all attributes from the file and put them into both the linked list
	 * and the hash map
	 * <p>
	 * 
	 * @throws IOException
	 */
	private void initAttribute() throws FileNotFoundException, IOException {
		
		System.out.println("Init list of attributes");
		BufferedReader bf = new BufferedReader(new FileReader(dir));

		String line = "undefined";
		try {
			while ((line = bf.readLine()) != null) {
				String[] elements = line.split(",");
				String name = elements[0];
				
				switch (elements[1]) {
				
				case IGNORE:
					attributes.add(new Attribute(name).setIgnored(true));
					System.out.printf("Added ignored attribute %s\n", name);
					break;
					
				case GENERAL:
					// String dataType = elements[2];
					// TODO: implement a way to handle more data types
					attributes.add(new GeneralAttribute(name));
					System.out.printf("Added general attribute %s\n", name);
					break;
					
				case WEIGHTED:
					switch (elements[2]) {
					
					case MULTIPLE:
						int numberOfChoices = Integer.parseInt(elements[3]);
						OneToMultipleAttribute temp = new OneToMultipleAttribute(name);
						for (int i = 0; i < numberOfChoices; i++) {
							temp.add(elements[4+i]);
						}
						attributes.add(temp);
						System.out.printf("Added multiple attribute %s\n", name);
						break;
						
					case SCALE:
						int from = Integer.parseInt(elements[3]);
						int to = Integer.parseInt(elements[4]);
						attributes.add(new ScaleAttribute(name).setFrom(from).setTo(to));
						System.out.printf("Added scale attribute %s\n", name);
						break;
						
					default:
						throw new Exception("Case not accepted, must be scale or multiple");
					}
					break;
					
				default:
					throw new Exception("Case not accepted, must be ignore, general, or weighted");
				}
			}
			
			System.out.printf("End of file %s\n\n", dir);
		} catch (Exception e) {
			throw new IOException(String.format("Attributes in file %s is corrupted.\nProgram failed on line: %s\n%s", dir, line, e.getMessage()));
		} finally {
			bf.close();
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
	 */
	public void addAttribute(Attribute attr) {

		// TODO: Finish the method, append a new line with provided attribute in
		// the correct format in the file with the provided dir

	}

	public LinkedList<Attribute> getAttributes() throws IOException {
		if (attributes.isEmpty())
			throw new IOException("List of attributes is not initialized");
		return attributes;
	}
}
