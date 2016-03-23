package io.match.helper;

import java.io.File;
import javafx.stage.FileChooser;

public class LoadFile {
	
	private static final String sampleURLFromQ = "data/";
	
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
