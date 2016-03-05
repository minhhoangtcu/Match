package io.match.gui.left;

import javafx.beans.property.SimpleStringProperty;

public class Row {
	private final SimpleStringProperty name;
	private final SimpleStringProperty match;
	
	
	public Row(String name, String match) {
		this.name = new SimpleStringProperty(name);
		this.match = new SimpleStringProperty(match);
	}
	
	public String getName() {
        return name.get();
    }
	
    public void setName(String fName) {
        name.set(fName);
    }
    
    public SimpleStringProperty nameProperty() {
    	return name;
    }
    
    public boolean getMatch() {
    	return match.get() == "X";
    }
    
    public void setMatch(String match) {
    	this.match.set(match);
    }
    
    public SimpleStringProperty matchProperty() {
    	return match;
    }
}
