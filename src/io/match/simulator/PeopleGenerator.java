package io.match.simulator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import io.match.datastructure.attributes.ScaleAttribute;
import io.match.reader.AttributesIO;
import io.match.reader.PeopleIO;

public class PeopleGenerator {

	private String attrURL;
	private AttributesIO aIO;
	private LinkedList<Attribute> attributes;

	/**
	 * Take the directory to the attributes file to generate random data
	 * 
	 * @param attrDir
	 *            directory to the attributes file.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public PeopleGenerator(String attrDir) throws FileNotFoundException, IOException {
		this.attrURL = attrDir;
		aIO = new AttributesIO(attrURL);
		aIO.readAttributes();
		attributes = aIO.getAttributes();
	}

	/**
	 * Generate a bunch of people without fixed attributes (not match yet)
	 * 
	 * @param numOfPeople number of people to generate
	 * @throws IOException the directories to the files are invalid
	 */
	public LinkedList<Person> generatePeople(int numOfPeople) {

		LinkedList<Person> list = new LinkedList<>();
		
		for (int i = 0; i < numOfPeople; i++) {
			// Generate random person with random name
			Person temp = new Person(RandomStringUtils.random(15, true, false));
			
			// Generate random attributes for the person
			for (Attribute attr : attributes) {
				switch (attr.getAttributeType()) {
				case GENERAL:
					temp.addGeneralAttribute(getGeneralAttribute(attr));
					break;
				case WEIGHTED_ONE_TO_MULTIPLE:
					temp.addOneToMultipleAttribute(getRandomOneToMUltipleAttribute(attr));
					break;
				case WEIGHTED_SCALE:
					temp.addScaleAttribute(getRandomScaleAttribute(attr));
					break;
				}
			}
			
			list.add(temp);
		}

		return list;
	}
	
	/**
	 * Generate a bunch of people without fixed attributes to specified files (not match yet)
	 * 
	 * @param dirPeople directory to the people dataset
	 * @param dirAttr directory to the fixed attribute dataset
	 * @param numOfPeople number of people to generate
	 * @throws IOException the directories to the files are invalid
	 */
	public void generatePeopleToFiles(String dirPeople, String dirAttr, int numOfPeople) throws IOException {
		
		wipeFile(dirPeople);
		wipeFile(dirAttr);
		
		PeopleIO io = new PeopleIO(dirPeople, dirAttr, attributes);
		
		for (Person person: generatePeople(numOfPeople)) {
			io.addPerson(person);
		}
		
	}
	
	private void wipeFile(String dir) throws IOException {
		BufferedWriter bf = new BufferedWriter(new FileWriter(dir));
		bf.close();
	}

	private GeneralAttribute getGeneralAttribute(Attribute attr) {
		return new GeneralAttribute(attr.getAttributeName(), RandomStringUtils.random(15, true, false));
	}

	private OneToMultipleAttribute getRandomOneToMUltipleAttribute(Attribute attr) {
		OneToMultipleAttribute converted = (OneToMultipleAttribute) attr;
		OneToMultipleAttribute temp = new OneToMultipleAttribute(converted.getAttributeName());

		int numOfPossibleChoices = converted.getNumOfPossibleChoices();
		String[] choices = converted.getPossibleChoices();

		// Set possibleChoices
		temp.setPossibleChoices(converted.getPossibleChoices());
		
		// Set random choice
		int randomChoice = (int) (Math.random() * numOfPossibleChoices);
		temp.setChoice(choices[randomChoice]);

		// Set random expecting choices
		int numOfExpectings = (int) (Math.random() * numOfPossibleChoices);
		for (int expectingIndex : getDistinctInt(numOfExpectings, numOfPossibleChoices)) {
			temp.addExpectingChoice(choices[expectingIndex]);
		}

		// Set random interest
		temp.setInterst(getRandomInterest());

		return temp;
	}

	private ScaleAttribute getRandomScaleAttribute(Attribute attr) {
		ScaleAttribute converted = (ScaleAttribute) attr;
		ScaleAttribute temp = new ScaleAttribute(converted.getAttributeName()).setFrom(converted.getFrom())
				.setTo(converted.getTo());
		int range = converted.getTo() - converted.getFrom();
		temp.setChoice((int) (converted.getFrom() + Math.random() * range));
		temp.setExpectingChoice((int) (converted.getFrom() + Math.random() * range));
		temp.setInterst(getRandomInterest());

		return temp;
	}

	private Interest getRandomInterest() {
		int randomInterst = (int) (Math.random() * 3);
		switch (randomInterst) {
		case 0:
			return Interest.NOT_IMPORTANT;
		case 1:
			return Interest.SOMEWHAT_IMPORTANT;
		case 2:
			return Interest.VERY_IMPORTANT;
		default:
			return null;
		}
	}

	private Integer[] getDistinctInt(int numOfInts, int range) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i <= numOfInts; i++) {
			int ranNum = (int) (Math.random() * range);
			while (set.contains(ranNum)) {
				ranNum = (int) (Math.random() * range);
			}
			set.add(ranNum);
		}
		return set.toArray(new Integer[numOfInts]);
	}
}
