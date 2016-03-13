package io.match.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Compare {
	
	public static ArrayList<Similarity> getTopSimilarities(Person first, Person second, int topN) {
		
		ArrayList<Similarity> temp = new ArrayList<>();
		
		
		return null;
	}

	/**
	 * Get the percentage matching from 2 people. This algorithm does not care about whether the person is a student or faculty.
	 * 
	 * @param first the first person
	 * @param second the second person
	 * @return
	 * @throws Exception 
	 */
	public static double getMatch(Person first, Person second) {
		
		isSameSize(first, second);
		int size = first.getAttributes().size();
		
		Iterator<Attribute> firstIterator = first.getAttributes().iterator();
		Iterator<Attribute> secondIterator = second.getAttributes().iterator();
		 
		int pointPossibleGainedByFirst = 0;
		int pointPossibleGainedBySecond = 0;
		int pointGainedByFirst = 0;
		int pointGainedBySecond = 0;
		
		/**
		 * Look through all the attributes the 2 people have and calculate the points
		 */
		for (int i = 0; i < size; i++) {
			
			Attribute firstAttr = firstIterator.next();
			Attribute secondAttr = secondIterator.next();
			Similarity temp = getSimilarity(firstAttr, secondAttr);
			pointGainedByFirst += temp.pointGainedByFirst;
			pointPossibleGainedByFirst += temp.pointPossibleGainedByFirst;
			pointGainedBySecond += temp.pointGainedBySecond;
			pointPossibleGainedBySecond += temp.pointPossibleGainedBySecond;
			
		}
		
		/**
		 * After looking through all the point, we need to compute the matching percentage
		 */
		float likeProbabilityFirst = (float) pointGainedByFirst/(float)pointPossibleGainedByFirst*100f;
		float likeProbabilitySecond = (float) pointGainedBySecond/(float)pointPossibleGainedBySecond*100f;
		double matchingProbability = Math.sqrt(likeProbabilityFirst*likeProbabilitySecond);
		
		return matchingProbability;
	}
	
	public static Similarity getSimilarity(Attribute firstAttr, Attribute secondAttr) {
		
		Similarity temp = new Similarity();

		if (!Compare.isSameAttribute(firstAttr, secondAttr))
			throw new IllegalArgumentException("The names of attributes of 2 people do not match!");
		
		switch (firstAttr.getAttributeType()) {
		case GENERAL:
			temp.pointGainedByFirst = 0;
			temp.pointGainedBySecond = 0;
			temp.pointPossibleGainedByFirst = 0;
			temp.pointPossibleGainedBySecond = 0;
			break;
			
		case WEIGHTED_ONE_TO_MULTIPLE:
			OneToMultipleAttribute firstAttrConverted = (OneToMultipleAttribute) firstAttr;
			OneToMultipleAttribute secondAttrConverted = (OneToMultipleAttribute) secondAttr;
			
			// Points (likelihood that A will like B)
			Interest firstInterest = firstAttrConverted.getInterst();
			int firstPointGained = MultiplePointCompute.getPoint(firstInterest);
			boolean isMatchExpectationFirst = Compare.isMatchAttribute(firstAttrConverted, secondAttrConverted);
			
			switch(firstInterest) {
			case NOT_IMPORTANT:
				temp.pointPossibleGainedByFirst = firstPointGained;
				temp.pointGainedByFirst = firstPointGained;
				break;
			case SOMEWHAT_IMPORTANT:
			case VERY_IMPORTANT:
				temp.pointPossibleGainedByFirst = firstPointGained;
				if (isMatchExpectationFirst)
					temp.pointGainedByFirst = firstPointGained;
				break;
			}
			
			// Points (likelihood that B will like A)
			Interest secondInterest = secondAttrConverted.getInterst();
			int secondPointGained = MultiplePointCompute.getPoint(secondInterest);
			boolean isMatchExpectationSecond = Compare.isMatchAttribute(secondAttrConverted, firstAttrConverted);
			
			switch(secondInterest) {
			case NOT_IMPORTANT:
				temp.pointPossibleGainedBySecond = secondPointGained;
				temp.pointGainedBySecond = secondPointGained;
				break;
			case SOMEWHAT_IMPORTANT:
			case VERY_IMPORTANT:
				temp.pointPossibleGainedBySecond = secondPointGained;
				if (isMatchExpectationSecond)
					temp.pointGainedBySecond = secondPointGained;
				break;
			}
			break;
			
		case WEIGHTED_SCALE:
			ScaleAttribute firstAttrConverted2 = (ScaleAttribute) firstAttr;
			ScaleAttribute secondAttrConverted2 = (ScaleAttribute) secondAttr;
			float pointGainedPercentage = ScalePointCompute.getPointPercentage(firstAttrConverted2, secondAttrConverted2);
			
			// Points (likelihood that A will like B)
			Interest firstInterest2 = firstAttrConverted2.getInterst();
			int firstPointGained2 = MultiplePointCompute.getPoint(firstInterest2);
			
			temp.pointPossibleGainedByFirst = firstPointGained2;
			if (firstInterest2 == Interest.NOT_IMPORTANT)
				temp.pointGainedByFirst = firstPointGained2;
			else
				temp.pointGainedByFirst = pointGainedPercentage*firstPointGained2;

			
			// Points (likelihood that A will like B)
			Interest secondInterest2 = secondAttrConverted2.getInterst();
			int secondPointGained2 = MultiplePointCompute.getPoint(secondInterest2);
			
			temp.pointPossibleGainedBySecond += secondPointGained2;
			if (secondInterest2 == Interest.NOT_IMPORTANT)
				temp.pointGainedBySecond += secondPointGained2;
			else
				temp.pointGainedBySecond += pointGainedPercentage*secondPointGained2;
			break;
		}
		
		float likeProbabilityFirst = (float) temp.pointGainedByFirst/(float) temp.pointPossibleGainedByFirst*100f;
		float likeProbabilitySecond = (float) temp.pointGainedBySecond/(float)temp.pointPossibleGainedBySecond*100f;
		temp.pointAverage = Math.sqrt(likeProbabilityFirst*likeProbabilitySecond);
		
		return temp;
	}
	
	static boolean isMatchAttribute(OneToMultipleAttribute firstAttr, OneToMultipleAttribute secondAttr) {
		for (String expecting: firstAttr.getExpectingChoice()) {
			if (expecting.equals(secondAttr.getChoice()))
				return true;
		}
		return false;
	}
	
	static boolean isSameAttribute(Attribute firstAttr, Attribute secondAttr) {
		return firstAttr.getAttributeName().equals(secondAttr.getAttributeName());
	}
	
	private static boolean isSameSize(Person first, Person second) {
		if (first.getAttributes().size() == second.getAttributes().size())
			return true;
		else
			throw new IllegalArgumentException("Numbers of attributes from 2 arguments do not match!");
	}

}
