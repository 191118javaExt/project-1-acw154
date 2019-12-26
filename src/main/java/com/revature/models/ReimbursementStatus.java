package com.revature.models;

public enum ReimbursementStatus {
	PENDING(0), APPROVED(1), DENIED(-1);
	
	private final int value;
	
	private ReimbursementStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
