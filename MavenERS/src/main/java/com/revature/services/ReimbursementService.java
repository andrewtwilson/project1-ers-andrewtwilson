package com.revature.services;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.Users;

public interface ReimbursementService {
	ReimbursementService currentImplementation = new ReimbursementServiceImpl();
	
	List<Reimbursement> getAllReimbursements(); 
	
	List<Reimbursement> getUsersReimbursements(int userId); 
	
	void addReimbursementRequest(Reimbursement reimbursement);
	
	void updateReimbursement(int reimbursementId, String sq, int userId, int statusId);
}
