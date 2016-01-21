package io.match.datastructure;

import io.match.datastructure.attributes.Classification;
import io.match.datastructure.attributes.Date;

public interface Person {
	
	public String getName();
	public String getEmail();
	public String getNationality();
	public String getMajor();
	public int getPhone();
	public Date getBirthday();
	public Classification getClassification();
	
}
