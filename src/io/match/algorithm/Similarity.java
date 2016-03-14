package io.match.algorithm;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Similarity implements Comparable<Similarity> {

	Attribute firstAttr, secondAttr;
	double pointGainedByFirst, pointGainedBySecond;
	double pointPossibleGainedByFirst, pointPossibleGainedBySecond;

	/**
	 * Create the class with no data. This constructor should only be
	 * initialized by the method getSimilarity in class Compare Compare in this
	 * package.
	 */
	Similarity() {
	}

	public String getAttributeName() {
		return firstAttr.getAttributeName();
	}

	/**
	 * @return the firstAttr
	 */
	public Attribute getFirstAttr() {
		return firstAttr;
	}

	/**
	 * @return the secondAttr
	 */
	public Attribute getSecondAttr() {
		return secondAttr;
	}

	/**
	 * Return the total point gained by both people by this similarity. The
	 * larger this number is, the heavier this similarity is going to affect the
	 * match probability.
	 * 
	 * @return the total point gained by both people by this similarity
	 */
	public double getTotalPointGained() {
		return pointGainedByFirst + pointGainedBySecond;
	}

	@Override
	public int compareTo(Similarity other) {
		if (getTotalPointGained() < other.getTotalPointGained())
			return -1;
		else if (getTotalPointGained() > other.getTotalPointGained())
			return 1;
		else
			return 0;
	}

}
