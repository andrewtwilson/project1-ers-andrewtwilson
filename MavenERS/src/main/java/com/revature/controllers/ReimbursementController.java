package com.revature.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.services.ReimbursementService;
import com.revature.util.ResponseMapper;

public class ReimbursementController {
	private Logger log = Logger.getRootLogger();
	private ReimbursementService rs = ReimbursementService.currentImplementation;
	private ObjectMapper om = new ObjectMapper();

	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		switch (method) {
		case "GET":
			processGet(req, resp);
			break;
		case "POST":
			processPost(req, resp);
			return;
		case "OPTIONS":
			return;
		default:
			resp.setStatus(404);
			break;
		}
	}

	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "ers";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		System.out.println(Arrays.toString(uriArray));
		if (uriArray.length == 1) {
			log.info("retreiving all reimbursements");
			List<Reimbursement> reimbursements = rs.getAllReimbursements();
			System.out.println(reimbursements);
			ResponseMapper.convertAndAttach(reimbursements, resp);
			resp.setStatus(200);
			return;
		} else if (uriArray.length == 3 && uriArray[1].equals("user")) {
			try {
				int id = Integer.parseInt(uriArray[2]);
				log.info("retreiving reimbursements of user with id: " + id);
				List<Reimbursement> reimbursements = rs.getUsersReimbursements(id);
				ResponseMapper.convertAndAttach(reimbursements, resp);
				resp.setStatus(200);
				return;
			} catch (NumberFormatException e) {
				resp.setStatus(400);
				return;
			}

		} else {
			resp.setStatus(404);
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException {
		String uri = req.getRequestURI();
		String context = "ers";
		uri = uri.substring(context.length() + 2, uri.length());
		if ("reimbursements".equals(uri)) {
			log.info("saving new reimbursement");
			
			java.util.Date utilDate = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(utilDate);
			java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
			
			Reimbursement reimbursement = new Reimbursement(100000.0, sq, "receipt again", 7,
					new ReimbursementStatus(1, "PENDING"), new ReimbursementType(2, "TRAVEL"));
			
			rs.addReimbursementRequest(reimbursement);
			resp.setStatus(200);
		} else {
			resp.setStatus(404);
			return;
		}
	}
}
