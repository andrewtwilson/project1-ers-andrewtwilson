package com.revature.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.Credential;
import com.revature.dto.TempReimbursement;
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
			break;
		case "PATCH":
			processPatch(req, resp);
			break;
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
		if (uriArray.length == 1) {
			// get all reimbursements

			if (req.getSession().getAttribute("id") != null) {
			log.info("retreiving all reimbursements");
			List<Reimbursement> reimbursements = rs.getAllReimbursements();
			ResponseMapper.convertAndAttach(reimbursements, resp);
			resp.setStatus(200);
			} else {
				resp.setStatus(403);
			}
			return;

		} else if (uriArray.length == 3 && "user".equals(uriArray[1])) {
			// get a users's reimbursements

			if (req.getSession().getAttribute("id") != null) {
				int id = Integer.parseInt(uriArray[2]);
				log.info("retreiving reimbursements of user with id: " + id);
				List<Reimbursement> reimbursements = rs.getUsersReimbursements(id);
				ResponseMapper.convertAndAttach(reimbursements, resp);
				resp.setStatus(200);
			} else {
				resp.setStatus(403);
			}
			return;

		} else if (uriArray.length == 2 && "test".equals(uriArray[1])) {
			ResponseMapper.convertAndAttach("Test Works", resp);
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
			String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(sq);
			
			TempReimbursement tr = om.readValue(req.getReader(), TempReimbursement.class);
			int typeId = 0;
			if ("LODGING".equals(tr.getType())) {
				typeId = 1;
			} else if ("TRAVEL".equals(tr.getType())) {
				typeId = 2;
			} else if ("FOOD".equals(tr.getType())) {
				typeId = 3;
			} else if ("OTHER".equals(tr.getType())) {
				typeId = 4;
			}

			Reimbursement reimbursement = new Reimbursement(tr.getAmount(), s, tr.getDescription(), (int) req.getSession().getAttribute("id"),
					new ReimbursementStatus(1, "PENDING"), new ReimbursementType(typeId, "TRAVEL"));

			rs.addReimbursementRequest(reimbursement);
			resp.setStatus(200);

		} else {
			resp.setStatus(404);
			return;
		}
	}

	private void processPatch(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException {
		String uri = req.getRequestURI();
		String context = "ers";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");

		if (uriArray.length == 2) {
			int reimbursementId = Integer.parseInt(uriArray[1]);
			log.info("updating reimbursement with id: " + reimbursementId);
			
			java.util.Date utilDate = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(utilDate);
			java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
			String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(sq);
			
			int userId = (int) req.getSession().getAttribute("id");
			
			int statusId = om.readValue(req.getReader(), int.class);
			
			rs.updateReimbursement(reimbursementId, s, userId, statusId);
			resp.setStatus(200);

			return;

		} else {
			resp.setStatus(404);
			return;
		}
	}
}
