package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementDAOImpl;

public class ReimbursementService {
	private ReimbursementDAO repository = new ReimbursementDAOImpl();
	
	public ReimbursementService(ReimbursementDAO repository) {
		this.repository = repository;
	}
	
	
	public Reimbursement findReimbursement(int reimb_id) {
		return repository.findReimbursement(reimb_id);
	}
	
//	public List<Reimbursement> findReimbursementsByStatus(ReimbursementStatus status){
//		return repository.findReimbursementsByStatus(status);
//	}
	
	public List<Reimbursement> findAllReimbursements(){
		return repository.findAllReimbursements();
	}
	
	public boolean submitReimbursement(Reimbursement reimb) {
		return repository.submitReimbursement(reimb);
	}
	
//	public boolean deleteReimbursement(int reimb_id) {
//		return repository.deleteReimbursement(reimb_id);
//	}
	
	public boolean approveReimbursement(int reimb_id, int resolvr_id) {
		return repository.approveReimbursement(reimb_id, resolvr_id);
	}
	
	public boolean denyReimbursement(int reimb_id, int resolvr_id) {
		return repository.denyReimbursement(reimb_id, resolvr_id);
	}
	
	public List<Reimbursement> findReimbursementsByAuthor(int author_id){
		return repository.findReimbursementsByAuthor(author_id);
	}
	
	
}
