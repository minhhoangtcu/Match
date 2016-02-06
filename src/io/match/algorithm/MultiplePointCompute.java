package io.match.algorithm;

import io.match.datastructure.attributes.Attribute.Interest;

public class MultiplePointCompute {
	
	private static final int NOT_IMPORTANT = 70;
	private static final int SOMEWHAT_IMPORTANT = 50;
	private static final int VERY_IMPORTANT = 100;
	
	public static int getPoint(Interest level) {
		switch (level) {
		case NOT_IMPORTANT:
			return NOT_IMPORTANT;
		case SOMEWHAT_IMPORTANT:
			return SOMEWHAT_IMPORTANT;
		case VERY_IMPORTANT:
			return VERY_IMPORTANT;
		default:
			throw new IllegalArgumentException("Passed level of Interest was not in defined cases");
		}
	}

}
