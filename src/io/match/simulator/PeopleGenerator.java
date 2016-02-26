package io.match.simulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import io.match.datastructure.Person;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;
import org.apache.commons.lang3.RandomStringUtils;

public class PeopleGenerator {
	
	private String attrURL;
	private AttributesIO aIO;
	
	public static void main(String[] args) {
		System.out.println(RandomStringUtils.random(15, true, false));
	}
	
	/**
	 * Take the directory to the attributes file to generate random data
	 * 
	 * @param attrDir directory to the attributes file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public PeopleGenerator(String attrDir) throws FileNotFoundException, IOException {
		this.attrURL = attrDir;
		aIO = new AttributesIO(attrURL);
		aIO.readAttributes();
	}
	
	public void generatePeople(String peopleDir, String faAttrDir, int numOfPeople) throws IOException {
		
		PeopleIO pIO = new PeopleIO(peopleDir, faAttrDir, aIO.getAttributes());
		pIO.removeAllOnFile();
		
		Person temp = new Person(RandomStringUtils.random(15, true, false));
		
		
	}

}
