package com.mydomain.model;

public class Banknote {
	public static final String TYPE_20 = "BankNote20";
	public static final String TYPE_50 = "BankNote50";
	public static final String TYPE_100 = "BankNote100";
	public static final String TYPE_500 = "BankNote500";
	public static final String TYPE_1000 = "BankNote1000";
	
	public static final int VALUE_20 = 20;
	public static final int VALUE_50 = 50;
	public static final int VALUE_100 = 100;
	public static final int VALUE_500 = 500;
	public static final int VALUE_1000 = 1000;
	
	private String type;
	private int value;
	
	public Banknote(String type) {
		this.type = type;
		if(TYPE_20.equals(type)) {
			value = VALUE_20;
		}
		else if(TYPE_50.equals(type)) {
			value = VALUE_50;
		}
		else if(TYPE_100.equals(type)) {
			value = VALUE_100;
		}
		else if(TYPE_500.equals(type)) {
			value = VALUE_500;
		}
		else if(TYPE_1000.equals(type)) {
			value = VALUE_1000;
		}
	}

	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banknote other = (Banknote) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	
}
