package io.match.algorithm;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.Interest;
import io.match.datastructure.attributes.OneToMultipleAttribute;
import io.match.datastructure.attributes.ScaleAttribute;

public class Similarity implements Comparable<Similarity> {

	Attribute firstAttr, secondAttr;
	double pointGainedByFirst, pointGainedBySecond;
	double pointPossibleGainedByFirst, pointPossibleGainedBySecond;
	double pointAverage;

	/**
	 * Create the class with no data. This constructor should only be
	 * initialized by the method getSimilarity in class Compare Compare in this
	 * package.
	 */
	Similarity() {}

	@Override
	public int compareTo(Similarity other) {
		if (pointAverage < other.pointAverage)
			return -1;
		else if (pointAverage > other.pointAverage)
			return 1;
		else
			return 0;
	}

}
