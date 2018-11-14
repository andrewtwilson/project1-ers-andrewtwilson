package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.Credential;
import com.revature.model.UserRoles;
import com.revature.model.Users;
import com.revature.services.UsersService;
import com.revature.util.ResponseMapper;

public class UserController {
	private Logger log = Logger.getRootLogger();
	private UsersService us = UsersService.currentImplementation;
	private ObjectMapper om = new ObjectMapper();

	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		switch (method) {
		case "GET":
			processGet(req, resp);
			return;
		case "POST":
			processPost(req, resp);
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
		System.out.println(Arrays.toString(uriArray));
		if (uriArray.length == 1) {
			try {
				log.info("retreiving all users");
				List<Users> users = us.getUsers();
				ResponseMapper.convertAndAttach(users, resp);
				resp.setStatus(200);
				return;
			} catch (NumberFormatException e) {
				resp.setStatus(404);
				return;
			}
		} else if (uriArray.length == 2) {
			try {
				int id = Integer.parseInt(uriArray[1]);
				log.info("retreiving user with id: " + id);
				Users user = us.getUserById(id);
				if (user != null) {
					ResponseMapper.convertAndAttach(user, resp);
					resp.setStatus(200);
				} else {
					resp.setStatus(404);
				}
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
		if ("users".equals(uri)) {
			log.info("saving new user");
			Users user = new Users("username", "password", "firstName", "lastName", "email",
					new UserRoles(1, "EMPLOYEE"));
			us.AddUser(user);
			resp.setStatus(200);
		} else if ("users/login".equals(uri)) {
			Credential cred = om.readValue(req.getReader(), Credential.class);
			log.info("attempting to log in with cred: " + cred);
			if (!us.login(cred, req.getSession())) {
				resp.setStatus(403);
			}
		} else {
			resp.setStatus(404);
			return;
		}
	}

}
