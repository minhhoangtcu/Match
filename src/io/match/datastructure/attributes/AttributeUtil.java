package io.match.datastructure.attributes;

public class AttributeUtil {
	
	private static final String VERY_IMPORTANT = "Very important";
	private static final String SOMEWHAT_IMPORTANT = "Somewhat important";
	private static final String NOT_IMPORTANT = "Not important";
	private static final String GENERAL = "General";
	private static final String WEIGHTED_ONE_TO_MULTIPLE = "Weighted - MC";
	private static final String WEIGHTED_SCALE = "Weighted - Scale";
	
	public static Interest getInterest(String input) throws Exception {
		switch (input) {
		case NOT_IMPORTANT:
			return Interest.NOT_IMPORTANT;
		case SOMEWHAT_IMPORTANT:
			return Interest.SOMEWHAT_IMPORTANT;
		case VERY_IMPORTANT:
			return Interest.VERY_IMPORTANT;
		}
		throw new Exception(String.format("Importance: %s is not %s, %s or %s\n", input, NOT_IMPORTANT,
				SOMEWHAT_IMPORTANT, VERY_IMPORTANT));
	}
	
	public static String getStringFromInterest(Interest input) {
		switch (input) {
		case NOT_IMPORTANT:
			return NOT_IMPORTANT;
		case SOMEWHAT_IMPORTANT:
			return SOMEWHAT_IMPORTANT;
		case VERY_IMPORTANT:
			return VERY_IMPORTANT;
		}
		throw new IllegalArgumentException("Invalid interest");
	}
	
	public static String getStringFromType(AttributeType input) {
		switch (input) {
		case GENERAL:
			return GENERAL;
		case WEIGHTED_ONE_TO_MULTIPLE:
			return WEIGHTED_ONE_TO_MULTIPLE;
		case WEIGHTED_SCALE:
			return WEIGHTED_SCALE;
		}
		throw new IllegalArgumentException("Invalid attribute type");
	}
}
