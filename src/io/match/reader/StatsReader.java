package io.match.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StatsReader {

	public static String readPeople(String url) {
		
		File file = new File(url);
		String fileName = file.getName();
		
		BufferedReader br;
		int numberOfEntry = 0;
		
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine(); // Skip first line
			String line;
			
			while ((line = br.readLine()) != null) {
				numberOfEntry++;
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			return e.toString();
		} catch (IOException e) {
			return e.toString();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<<< %s LOADED SUCCESSFULLY >>>\n", fileName));
		sb.append(String.format("%d people found\n\n", numberOfEntry));
		
		return sb.toString();
	}
}
