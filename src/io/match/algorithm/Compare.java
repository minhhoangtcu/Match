package io.match.algorithm;

import java.util.Iterator;

import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.Attribute.Interest;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.MultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Compare {
	
	

	/**
	 * Get the percentage matching from 2 people. This algorithm does not care about whether the person is a student or faculty.
	 * 
	 * @param first the first person
	 * @param second the second person
	 * @return
	 * @throws Exception 
	 */
	public static double getMatch(Person first, Person second) throws Exception {
		
		if (first.getAttributes().size() != second.getAttributes().size())
			throw new IllegalArgumentException("Numbers of attributes from 2 arguments do not match!");
		
		Iterator<Attribute> firstIterator = first.getAttributes().iterator();
		Iterator<Attribute> secondIterator = second.getAttributes().iterator();
		 
		int posiblePointFirst = 0;
		int posiblePointSecond = 0;
		int gainedPointFirst = 0;
		int gainedPointSecond = 0;
		
		/**
		 * Look through all the attributes the 2 people have and calculate the points
		 */
		for (int i = 0; i < first.getAttributes().size(); i++) {
			
			Attribute firstAttr = firstIterator.next();
			Attribute secondAttr = secondIterator.next();
			
			if (!isSameAttribute(firstAttr, secondAttr))
				throw new Exception("The names of attributes of 2 people do not match!");
			
			if (firstAttr instanceof GeneralAttribute && secondAttr instanceof GeneralAttribute) {
				continue; // We do not have to deal with general attribute, for now
			}
			else if (firstAttr instanceof MultipleAttribute && secondAttr instanceof MultipleAttribute) {
				
				// TODO: Clean up duplicate codes
				
				MultipleAttribute firstAttrConverted = (MultipleAttribute) firstAttr;
				MultipleAttribute secondAttrConverted = (MultipleAttribute) secondAttr;
				boolean isSame = firstAttrConverted.isSame(secondAttrConverted);
				
				// Points (likelihood that A will like B)
				Interest firstInterest = firstAttrConverted.getInterst();
				int firstPointGained = MultiplePointCompute.getPoint(firstInterest);
				
				switch(firstInterest) {
				case NOT_IMPORTANT:
					posiblePointFirst += firstPointGained;
					gainedPointFirst += firstPointGained;
					break;
				case SOMEWHAT_IMPORTANT:
				case VERY_IMPORTANT:
					posiblePointFirst += firstPointGained;
					if (isSame)
						gainedPointFirst += firstPointGained;
					break;
				}
				
				// Points (likelihood that B will like A)
				Interest secondInterest = secondAttrConverted.getInterst();
				int secondPointGained = MultiplePointCompute.getPoint(secondInterest);
				
				switch(secondInterest) {
				case NOT_IMPORTANT:
					posiblePointSecond += secondPointGained;
					gainedPointSecond += secondPointGained;
					break;
				case SOMEWHAT_IMPORTANT:
				case VERY_IMPORTANT:
					posiblePointSecond += secondPointGained;
					if (isSame)
						gainedPointSecond += secondPointGained;
					break;
				}
				
				
			}
			else if (firstAttr instanceof ScaleAttribute && secondAttr instanceof ScaleAttribute) {
				
				// TODO: Clean up duplicate codes
				
				ScaleAttribute firstAttrConverted = (ScaleAttribute) firstAttr;
				ScaleAttribute secondAttrConverted = (ScaleAttribute) secondAttr;
				float pointGainedPercentage = ScalePointCompute.getPoint(firstAttrConverted, secondAttrConverted)/ScalePointCompute.getMax();
				
				// Points (likelihood that A will like B)
				Interest firstInterest = firstAttrConverted.getInterst();
				int firstPointGained = MultiplePointCompute.getPoint(firstInterest);
				
				posiblePointFirst += firstPointGained;
				if (firstInterest == Interest.NOT_IMPORTANT)
					gainedPointFirst += firstPointGained;
				else
					gainedPointFirst += pointGainedPercentage*firstPointGained;

				
				// Points (likelihood that A will like B)
				Interest secondInterest = secondAttrConverted.getInterst();
				int secondPointGained = MultiplePointCompute.getPoint(secondInterest);
				
				posiblePointSecond += secondPointGained;
				if (secondInterest == Interest.NOT_IMPORTANT)
					gainedPointSecond += secondPointGained;
				else
					gainedPointSecond += pointGainedPercentage*secondPointGained;
			}
			else
				throw new Exception("The type of attributes of 2 people do not match!");
		}
		
		/**
		 * After looking through all the point, we need to compute the matching percentage
		 */
		float likeProbabilityFirst = (float) gainedPointFirst/(float)posiblePointFirst*100f;
		float likeProbabilitySecond = (float) gainedPointSecond/(float)posiblePointSecond*100f;
		double matchingProbability = Math.sqrt(likeProbabilityFirst*likeProbabilitySecond);
		
		return matchingProbability;
	}
	
	private static boolean isSameAttribute(Attribute firstAttr, Attribute secondAttr) {
		return firstAttr.getAttributeName().equals(secondAttr.getAttributeName());
	}
	
	

}
