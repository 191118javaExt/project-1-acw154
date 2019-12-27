package com.revature.models;

import java.util.HashMap;
import java.util.Map;

public enum UserRoles {
	EMPLOYEE(1), FINANCEMANAGER(2);
	
	private final int value;
	private static Map map = new HashMap<>();

    private UserRoles(int value) {
        this.value = value;
    }

    static {
        for (UserRoles UserRoles : UserRoles.values()) {
            map.put(UserRoles.value, UserRoles);
        }
    }

    public static UserRoles valueOf(int UserRoles) {
        return (UserRoles) map.get(UserRoles);
    }

    public int getValue() {
        return value;
    }
}
