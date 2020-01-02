package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementDAOImpl;

public class ReimbursementService {
	private static ReimbursementDAO repository = new ReimbursementDAOImpl();
	
	public static Reimbursement findReimbursement(int reimb_id) {
		return repository.findReimbursement(reimb_id);
	}
	
	public static List<Reimbursement> findReimbursementsByStatus(ReimbursementStatus status){
		return repository.findReimbursementsByStatus(status);
	}
	
	public static List<Reimbursement> findAllReimbursements(){
		return repository.findAllReimbursements();
	}
	
	public static boolean submitReimbursement(Reimbursement reimb) {
		return repository.submitReimbursement(reimb);
	}
	
	public static boolean deleteReimbursement(int reimb_id) {
		return repository.deleteReimbursement(reimb_id);
	}
	
	public static boolean approveReimbursement(int reimb_id, int resolvr_id) {
		return repository.approveReimbursement(reimb_id, resolvr_id);
	}
	
	public static boolean denyReimbursement(int reimb_id, int resolvr_id) {
		return repository.denyReimbursement(reimb_id, resolvr_id);
	}
	
	public static List<Reimbursement> findReimbursementsByAuthor(int author_id){
		return repository.findReimbursementsByAuthor(author_id);
	}
	
	
}
