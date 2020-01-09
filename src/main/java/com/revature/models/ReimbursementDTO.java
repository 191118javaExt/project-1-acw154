package com.revature.models;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ReimbursementDTO {
	private int reimb_id;
	private double amount;
	private String submitted;
	private String resolved;
	private String base64receipt;
	private String desc;
	private int author_id;
	private int resolver_id;
	private String status_id;
	private String type_id;
	
	public ReimbursementDTO() {
		super();
	}

	public ReimbursementDTO(int reimb_id, double amount, String submitted, String resolved, String base64receipt, String desc,
			int author_id, int resolver_id, String status_id, String type_id) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.base64receipt = base64receipt;
		this.desc = desc;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
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

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getBase64Receipt() {
		return base64receipt;
	}

	public void setBase64Receipt(String base64receipt) {
		this.base64receipt = base64receipt;
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

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
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
		result = prime * result + ((base64receipt == null) ? 0 : base64receipt.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + reimb_id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver_id;
		result = prime * result + ((status_id == null) ? 0 : status_id.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type_id == null) ? 0 : type_id.hashCode());
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
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author_id != other.author_id)
			return false;
		if (base64receipt == null) {
			if (other.base64receipt != null)
				return false;
		} else if (!base64receipt.equals(other.base64receipt))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
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
		if (status_id == null) {
			if (other.status_id != null)
				return false;
		} else if (!status_id.equals(other.status_id))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type_id == null) {
			if (other.type_id != null)
				return false;
		} else if (!type_id.equals(other.type_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [reimb_id=" + reimb_id + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", base64receipt=" + base64receipt + ", desc=" + desc + ", author_id="
				+ author_id + ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id
				+ "]";
	}

	
	
}
