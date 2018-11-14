package com.revature.daos;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.Users;

public interface ReimbursementDao {
	ReimbursementDao currentImplementation = new ReimbursementDaoJdbc();

	List<Reimbursement> getReimbursements(); 
	
	List<Reimbursement> getReimbursementByUser(int userId); 
	
	void addReimbursement(Reimbursement reimbursement);
}
