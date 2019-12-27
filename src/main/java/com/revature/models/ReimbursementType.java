package com.revature.models;

import java.util.HashMap;
import java.util.Map;

public enum ReimbursementType {
	LODGING(1), TRAVEL(2), FOOD(3), OTHER(4);
	
	private final int value;
	private static Map map = new HashMap<>();

    private ReimbursementType(int value) {
        this.value = value;
    }

    static {
        for (ReimbursementType ReimbursementType : ReimbursementType.values()) {
            map.put(ReimbursementType.value, ReimbursementType);
        }
    }

    public static ReimbursementType valueOf(int ReimbursementType) {
        return (ReimbursementType) map.get(ReimbursementType);
    }

    public int getValue() {
        return value;
    }
}