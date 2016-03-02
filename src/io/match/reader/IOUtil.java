package io.match.reader;

public class IOUtil {
	
	public static String getData(String data) {
		return data.replaceAll("\"", "").trim();
	}

}
