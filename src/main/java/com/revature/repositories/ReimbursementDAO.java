package com.revature.repositories;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;

public interface ReimbursementDAO {
	public Reimbursement findReimbursement(int reimb_id);
	public List<Reimbursement> findReimbursementsByStatus(ReimbursementStatus status);
	public List<Reimbursement> findAllReimbursements();
	public boolean submitReimbursement(Reimbursement reimb);
	public boolean deleteReimbursement(int reimb_id);
	public boolean approveReimbursement(int reimb_id, int resolvr_id);
	public boolean denyReimbursement(int reimb_id, int resolvr_id);
	public List<Reimbursement> findReimbursementsByAuthor(int author_id);
	
	
}
