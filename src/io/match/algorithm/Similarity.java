package io.match.algorithm;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Similarity implements Comparable<Similarity> {
	
	private Attribute firstAttr, secondAttr;
	private double pointGainedByFirst, pointGainedBySecond;
	private double pointPossibleGainedByFirst, pointPossibleGainedBySecond;
	private double pointAverage;
	
	public Similarity(Attribute firstAttr, Attribute secondAttr) {
		this.firstAttr = firstAttr;
		this.secondAttr = secondAttr;
		compute();
	}
	
	private void compute() {
		
		if (!Compare.isSameAttribute(firstAttr, secondAttr))
			throw new IllegalArgumentException("The names of attributes of 2 people do not match!");
		
		switch (firstAttr.getAttributeType()) {
		case GENERAL:
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
				pointPossibleGainedByFirst = firstPointGained;
				pointGainedByFirst = firstPointGained;
				break;
			case SOMEWHAT_IMPORTANT:
			case VERY_IMPORTANT:
				pointPossibleGainedByFirst = firstPointGained;
				if (isMatchExpectationFirst)
					pointGainedByFirst += firstPointGained;
				break;
			}
			
			// Points (likelihood that B will like A)
			Interest secondInterest = secondAttrConverted.getInterst();
			int secondPointGained = MultiplePointCompute.getPoint(secondInterest);
			boolean isMatchExpectationSecond = Compare.isMatchAttribute(secondAttrConverted, firstAttrConverted);
			
			switch(secondInterest) {
			case NOT_IMPORTANT:
				pointPossibleGainedBySecond = secondPointGained;
				pointGainedBySecond = secondPointGained;
				break;
			case SOMEWHAT_IMPORTANT:
			case VERY_IMPORTANT:
				pointPossibleGainedBySecond = secondPointGained;
				if (isMatchExpectationSecond)
					pointGainedBySecond = secondPointGained;
				break;
			}
			
		case WEIGHTED_SCALE:
			ScaleAttribute firstAttrConverted2 = (ScaleAttribute) firstAttr;
			ScaleAttribute secondAttrConverted2 = (ScaleAttribute) secondAttr;
			float pointGainedPercentage = ScalePointCompute.getPointPercentage(firstAttrConverted2, secondAttrConverted2);
			
			// Points (likelihood that A will like B)
			Interest firstInterest2 = firstAttrConverted2.getInterst();
			int firstPointGained2 = MultiplePointCompute.getPoint(firstInterest2);
			
			pointPossibleGainedByFirst = firstPointGained2;
			if (firstInterest2 == Interest.NOT_IMPORTANT)
				pointGainedByFirst = firstPointGained2;
			else
				pointGainedByFirst = pointGainedPercentage*firstPointGained2;

			
			// Points (likelihood that A will like B)
			Interest secondInterest2 = secondAttrConverted2.getInterst();
			int secondPointGained2 = MultiplePointCompute.getPoint(secondInterest2);
			
			pointPossibleGainedBySecond += secondPointGained2;
			if (secondInterest2 == Interest.NOT_IMPORTANT)
				pointGainedBySecond += secondPointGained2;
			else
				pointGainedBySecond += pointGainedPercentage*secondPointGained2;
		}
	}
	
	@Override
	public int compareTo(Similarity other) {
		if (pointAverage < other.pointAverage)
			return -1;
		else if (pointAverage > other.pointAverage) // TODO: difficult to compare double anyway
			return 1;
		else
			return 0;
	}
	
}
