package io.match.helper;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.stage.FileChooser;

public class LoadFile {
	private static String fileURL;
	private static File file;
	private static final String sampleURLFromQ = "data/";
	
	public static String getURL()throws FileNotFoundException {
		file = getFile();
		if (file == null) {
			throw new FileNotFoundException("File Not Found");
		} else {
			return file.getAbsolutePath();
		}
		
		
	}
	
	public static File getFile() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(sampleURLFromQ));
		FileChooser.ExtensionFilter filter = 
				new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		chooser.getExtensionFilters().add(filter);
		File file = chooser.showOpenDialog(null);
		return file;
	}
}
