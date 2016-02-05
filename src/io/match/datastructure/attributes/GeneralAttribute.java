package io.match.datastructure.attributes;

public class GeneralAttribute extends Attribute {
	
	private String data;
	
	public GeneralAttribute(String name) {
		super(name);
		attributeType = AttributeType.GENERAL;
	}
	
	public GeneralAttribute(String name, String data) {
		super(name);
		this.data = data;
		attributeType = AttributeType.GENERAL;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
