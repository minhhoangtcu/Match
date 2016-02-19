package io.match.reader;

import io.match.datastructure.attributes.Interest;

public class IOUtil {
	
	private static final String VERY_IMPORTANT = "Very important";
	private static final String SOMEWHAT_IMPORTANT = "Somewhat important";
	private static final String NOT_IMPORTANT = "Not important";
	
	public static Interest readInterest(String input) throws Exception {
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

	public static String getData(String data) {
		return data.replaceAll("\"", "");
	}

}
