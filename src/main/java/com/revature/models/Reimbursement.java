package com.revature.models;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Reimbursement {
	private int reimb_id;
	private double amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private byte[] receipt;
	private String desc;
	private int author_id;
	private int resolver_id;
	private int status_id;
	private int type_id;
	
	public Reimbursement(int reimb_id, double amount, LocalDateTime submitted, LocalDateTime resolved, byte[] receipt,
			String desc, int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.receipt = receipt;
		this.desc = desc;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	
	public Reimbursement(double amount, LocalDateTime submitted, LocalDateTime resolved, byte[] receipt, String desc,
			int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.receipt = receipt;
		this.desc = desc;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}
	
	


	public Reimbursement(double amount, String desc, byte[] receipt, int author_id,
			int status_id, int type_id) {
		super();
		this.amount = amount;
		this.desc = desc;
		this.receipt = receipt;
		this.author_id = author_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}


	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author_id;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + reimb_id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver_id;
		result = prime * result + status_id;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + type_id;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author_id != other.author_id)
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver_id != other.resolver_id)
			return false;
		if (status_id != other.status_id)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", receipt=" + Arrays.toString(receipt) + ", desc=" + desc + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
	
	
	
}