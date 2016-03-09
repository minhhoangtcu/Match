package io.match.gui.center.manage;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import io.match.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class DisplayPersonController {
	
	// Required
	private Model model;
	private MainController mController;
	private Object object;
	
	// GUI's
	@FXML
	private Button btnAddNew;
	
	@FXML
	private Button btnModify;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private ScrollPane scrollPane;
	
	public void setModel(Model model) {
		this.model = model;
	}

	public void setMainController(MainController controller) {
		mController = controller;
	}
	
	public void setObject(Object object) {
		this.object = object;
		if (object instanceof io.match.datastructure.Person) popupPerson((Person) object);
		if (object instanceof io.match.datastructure.attributes.Attribute) popupAttribute((Attribute) object);
		
	}

	private void popupPerson(Person person) {
		for (Attribute attribute: person.getAttributes()) {
			if (attribute.getAttributeType() == AttributeType.GENERAL) {
				
			} else if (attribute.getAttributeType() == AttributeType.WEIGHTED_ONE_TO_MULTIPLE) {
				System.out.println("weighted one to multiple");
			} else if (attribute.getAttributeType() == AttributeType.WEIGHTED_SCALE) {
				System.out.println("weighted scale");
			}
		}
	}
	
	private void popupAttribute(Attribute attribute) {
		System.out.println(attribute.getAttributeName());
	}
	
	@FXML
	private void add() {
		System.out.println("From add: DisplayPersonController");
	}
	
	@FXML
	private void modify() {
		System.out.println("From modify: DisplayPersonController");	
	}
	
	@FXML
	private void delete() {
		System.out.println("From delete: DisplayPersonController");
	}
}
