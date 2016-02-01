package io.match.datastructure.attributes;

public class GeneralAttribute extends Attribute {
	
	private String data;
	
	public GeneralAttribute(String name) {
		super(name);
		weight = Weight.GENERAL;
	}
	
	public GeneralAttribute(String name, String data) {
		super(name);
		this.data = data;
		weight = Weight.GENERAL;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
