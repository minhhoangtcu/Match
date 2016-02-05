package io.match.algorithm;

import io.match.datastructure.attributes.ScaleAttribute;

public class ScalePointCompute {
	
	private static final int DIFF_0 = 100, DIFF_1 = 50, DIFF_2 = 20, DIFF_3 = 10, DIFF_4 = 0;

	public static int getPoint(ScaleAttribute one, ScaleAttribute other) {
		
		int to = one.getTo();
		int from = one.getFrom();
		
		if (to != other.getTo() | from != other.getFrom()) {
			throw new IllegalArgumentException("The two attributes do not have similar scales");
		}

		float diffNormalized = Math.abs(one.getChoice() - other.getChoice()) / (to - from);
		// If diffNormalized = 0, there is no differences between the two
		// choice.
		if (0 <= diffNormalized && diffNormalized < 0.2)
			return DIFF_0;
		else if (0.2 <= diffNormalized && diffNormalized < 0.4)
			return DIFF_1;
		else if (0.4 <= diffNormalized && diffNormalized < 0.6)
			return DIFF_2;
		else if (0.6 <= diffNormalized && diffNormalized < 0.8)
			return DIFF_3;
		else
			return DIFF_4;
	}
	
	public static int getMax() {
		return DIFF_0;
	}
}
