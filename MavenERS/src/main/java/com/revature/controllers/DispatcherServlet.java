package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DispatcherServlet extends HttpServlet {
	private Logger log = Logger.getRootLogger();
	private UserController uc = new UserController();
	private ReimbursementController rc = new ReimbursementController();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.addHeader("Access-Control-Allow-Origin", "http://1810ersapi-env.2vmbjepcwy.us-east-2.elasticbeanstalk.com/");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
		String uri = req.getRequestURI();
		String context = "ers";
		uri = uri.substring(context.length() + 2, uri.length());
		log.debug("request made with uri: " + uri);
		if (uri.startsWith("users")) {
			uc.process(req, resp);
		} 
		else if (uri.startsWith("reimbursements")) {
			rc.process(req, resp);
		} else {
			resp.setStatus(404);
		}
	}

}
