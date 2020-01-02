package com.revature.models;

public class ReviewTemplate {
	private String reimb_id;
	private String reimb_status;
	
	
	public ReviewTemplate() {
		super();
	}


	public ReviewTemplate(String reimb_id, String reimb_status) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_status = reimb_status;
	}


	public String getReimb_id() {
		return reimb_id;
	}


	public void setReimb_id(String reimb_id) {
		this.reimb_id = reimb_id;
	}


	public String getReimb_status() {
		return reimb_status;
	}


	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimb_id == null) ? 0 : reimb_id.hashCode());
		result = prime * result + ((reimb_status == null) ? 0 : reimb_status.hashCode());
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
		ReviewTemplate other = (ReviewTemplate) obj;
		if (reimb_id == null) {
			if (other.reimb_id != null)
				return false;
		} else if (!reimb_id.equals(other.reimb_id))
			return false;
		if (reimb_status == null) {
			if (other.reimb_status != null)
				return false;
		} else if (!reimb_status.equals(other.reimb_status))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReviewTemplate [reimb_id=" + reimb_id + ", reimb_status=" + reimb_status + "]";
	}
	
	
	
}
