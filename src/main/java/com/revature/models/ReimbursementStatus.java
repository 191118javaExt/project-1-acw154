package com.revature.models;

import java.util.HashMap;
import java.util.Map;

public enum ReimbursementStatus {
	PENDING(0), APPROVED(1), DENIED(-1);
	
	private final int value;
	private static Map map = new HashMap<>();

    private ReimbursementStatus(int value) {
        this.value = value;
    }

    static {
        for (ReimbursementStatus ReimbursementStatus : ReimbursementStatus.values()) {
            map.put(ReimbursementStatus.value, ReimbursementStatus);
        }
    }

    public static ReimbursementStatus valueOf(int ReimbursementStatus) {
        return (ReimbursementStatus) map.get(ReimbursementStatus);
    }

    public int getValue() {
        return value;
    }
}
