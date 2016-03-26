package io.match.gui.center.manage;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ReadPersonComponentPopup {
	public static String readTextField(
			HashMap<String, Object> tableContent, String key) {
		TextField tf = (TextField) tableContent.get(key);
		String newValue = tf.getText();
		return newValue;
	}
	
	public static String readMultipleChoices(
			HashMap<String, Object> tableContent, String key) {
		ToggleGroup group = (ToggleGroup) tableContent.get(key);
		RadioButton radioButton = (RadioButton) group.getSelectedToggle();
		String newOption = radioButton.getText();
		return newOption;
	}
	
	public static String[] readMultipleOptions(
			HashMap<String, Object> tableContent, String key) {
		ArrayList<CheckBox> choices = (ArrayList<CheckBox>) tableContent.get(key);
		ArrayList<String> newChoices = new ArrayList<String>();
		for (CheckBox possibleChoice: choices) {
			if (possibleChoice.isSelected()) {
				newChoices.add(possibleChoice.getText());
			}
		}
		return (String[]) newChoices.toArray();
	}
	
	public static double readSlider(
			HashMap<String, Object> tableContent, String key) {
		Slider slider = (Slider) tableContent.get(key);
		double newInput = slider.getValue();
		return newInput;
	}
}
