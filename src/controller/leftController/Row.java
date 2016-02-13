package controller.leftController;

import javafx.beans.property.SimpleStringProperty;

public class Row {
	private final SimpleStringProperty name;
	
	public Row(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }
}
