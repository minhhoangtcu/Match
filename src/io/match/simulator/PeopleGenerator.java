package io.match.simulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;

public class PeopleGenerator {
	
	private String attrURL;
	private AttributesIO aIO;
	private LinkedList<Attribute> attributes;
	
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
		attributes = aIO.getAttributes();
	}
	
	public void generatePeople(String peopleDir, String faAttrDir, int numOfPeople) throws IOException {
		
		PeopleIO pIO = new PeopleIO(peopleDir, faAttrDir, aIO.getAttributes());
		pIO.removeAllOnFile();
		
		Person temp = new Person(RandomStringUtils.random(15, true, false));
		for (Attribute attr: attributes) {
			switch (attr.getAttributeType()) {
			case GENERAL:
			case WEIGHTED_ONE_TO_MULTIPLE:
				temp.addOneToMultipleAttribute(getRandomOneToMUltipleAttribute(attr));
				break;
			case WEIGHTED_SCALE:
			}
			
		}
	}
	
	private GeneralAttribute getGeneralAttribute(Attribute attr) {
		return new GeneralAttribute(attr.getAttributeName(), )
	}
	
	private OneToMultipleAttribute getRandomOneToMUltipleAttribute(Attribute attr) {
		OneToMultipleAttribute converted = (OneToMultipleAttribute) attr; 
		OneToMultipleAttribute temp = new OneToMultipleAttribute(converted.getAttributeName());
		
		int numOfPossibleChoices = converted.getNumOfPossibleChoices();
		String[] choices = converted.getPossibleChoices();
		
		// Set random choice 
		int randomChoice = (int) Math.random()*numOfPossibleChoices;
		temp.setChoice(choices[randomChoice]);
		
		// Set possibleChoices
		for (String possibleChoices: converted.getPossibleChoices()) {
			temp.addPossibleChoice(possibleChoices);
		}
		
		// Set random expecting choices
		for (int expectingIndex: getDistinctInt((int)Math.random()*numOfPossibleChoices, numOfPossibleChoices)) {
			temp.addExpectingChoice(choices[expectingIndex]);
		}
		
		// Set random interest
		int randomInterst = (int) Math.random()*3;
		switch (randomInterst) {
		case 0:
			temp.setInterst(Interest.NOT_IMPORTANT);
			break;
		case 1:
			temp.setInterst(Interest.SOMEWHAT_IMPORTANT);
			break;
		case 2:
			temp.setInterst(Interest.VERY_IMPORTANT);
			break;
		}
		
		return temp;
	}

	private Integer[] getDistinctInt(int numOfInts, int range) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < numOfInts; i++) {
			int ranNum = (int) Math.random()*range; 
			while (set.contains(ranNum)) {
				ranNum = (int) Math.random()*range;
			}
			set.add(ranNum);
		}
		return set.toArray(new Integer[numOfInts]);
	}
}
