package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.Users;

public class ReimbursementServiceImpl implements ReimbursementService{
	private ReimbursementDao rd = ReimbursementDao.currentImplementation;

	public List<Reimbursement> getAllReimbursements() {
		return rd.getReimbursements();
	}

	public List<Reimbursement> getUsersReimbursements(int userId) {
		return rd.getReimbursementByUser(userId);
	}

	public void addReimbursementRequest(Reimbursement reimbursement) {
		rd.addReimbursement(reimbursement);
	}

	@Override
	public void updateReimbursement(int reimbursementId, String sq, int userId, int statusId) {
		rd.updateReimbursement(reimbursementId, sq, userId, statusId);
	}
}
