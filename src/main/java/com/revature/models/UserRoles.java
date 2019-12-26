package com.revature.models;

public enum UserRoles {
	EMPLOYEE(1), FINANCEMANAGER(2);
	
	private final int value;
	
	private UserRoles(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
