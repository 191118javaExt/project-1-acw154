package com.revature.models;

public class SubmitTemplate {
	private String amount;
	private String description;
	private String reimb_type;
	private String receipt;
	
	public SubmitTemplate() {
		super();
	}

	public SubmitTemplate(String amount, String description, String reimb_type, String receipt) {
		super();
		this.amount = amount;
		this.description = description;
		this.reimb_type = reimb_type;
		this.receipt = receipt;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((reimb_type == null) ? 0 : reimb_type.hashCode());
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
		SubmitTemplate other = (SubmitTemplate) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimb_type == null) {
			if (other.reimb_type != null)
				return false;
		} else if (!reimb_type.equals(other.reimb_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubmitTemplate [amount=" + amount + ", description=" + description + ", reimb_type=" + reimb_type
				+ ", receipt=" + receipt + "]";
	}
	
	
	
	
}
