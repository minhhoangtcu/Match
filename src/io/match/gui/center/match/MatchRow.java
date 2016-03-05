package io.match.gui.center.match;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatchRow {
	
	private final SimpleStringProperty studentName;
	private final SimpleStringProperty facultyName;
	private final SimpleDoubleProperty probability;
	
	public MatchRow(String studentName, String facultyName, double probability) {
		this.studentName = new SimpleStringProperty(studentName);
		this.facultyName = new SimpleStringProperty(facultyName);
		this.probability = new SimpleDoubleProperty(probability);
	}
	
	public String getStudentName() {
        return studentName.get();
    }
	
    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }
    
    public SimpleStringProperty studentNameProperty() {
    	return studentName;
    }
    
    public String getFacultyName() {
        return facultyName.get();
    }
	
    public void setFacultyName(String facultyName) {
    	this.facultyName.set(facultyName);
    }
    
    public SimpleStringProperty facultyNameProperty() {
    	return facultyName;
    }
    
    public double getProbability() {
        return probability.get();
    }
	
    public void setProbability(double probability) {
    	this.probability.set(probability);
    }
    
    public SimpleDoubleProperty probabilityProperty() {
    	return probability;
    }
}
