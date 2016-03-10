package io.match.gui.left;

import javafx.beans.property.SimpleStringProperty;

public class TableRow {
	
	// Required names. These variables cannot be changed without the changes in TablePopulator.
	private final SimpleStringProperty name;
	private final SimpleStringProperty value;
	
	
	public TableRow(String name, String value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}
	
	/*
	 * NAME
	 */
	public String getName() {
        return name.get();
    }
	
    public void setName(String fName) {
        name.set(fName);
    }
    
    public SimpleStringProperty nameProperty() {
    	return name;
    }
    
    /*
     * VALUE
     */
    public String getKey() {
    	return value.get();
    }
    
    public void setKey(String match) {
    	this.value.set(match);
    }
    
    public SimpleStringProperty valueProperty() {
    	return value;
    }
}
