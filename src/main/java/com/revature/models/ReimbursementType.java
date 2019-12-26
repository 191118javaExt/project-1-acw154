package com.revature.models;

public enum ReimbursementType {
	LODGING(1), TRAVEL(2), FOOD(3), OTHER(4);
	
	private final int value;
	
	private ReimbursementType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	

}
