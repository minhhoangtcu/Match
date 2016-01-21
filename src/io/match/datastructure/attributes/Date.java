package io.match.datastructure.attributes;

public class Date {

	private int day, month, year;
	
	public Date(int day, int month, int year) {
		setDate(day, month, year);
	}
	
	public void setDate(int day, int month, int year) {
		if (validDay(day) && validMonth(month) && validYear(year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	
	private boolean validDay(int day) {
		if (day < 1 | day > 31)
			throw new IllegalArgumentException("Invalid day");
		return true;
	}
	
	private boolean validMonth(int month) {
		if (month < 1 | month > 12)
			throw new IllegalArgumentException("Invalid month");
		return true;
	}
	
	private boolean validYear(int year) {
		if (year < 0)
			throw new IllegalArgumentException("Invalid year");
		return true;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		validDay(day);
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		validMonth(month);
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		validYear(year);
		this.year = year;
	}
}
