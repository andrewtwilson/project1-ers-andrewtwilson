package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UsersDao;
import com.revature.dto.Credential;
import com.revature.model.Users;

public class UsersServiceImpl implements UsersService{
	UsersDao ud = UsersDao.currentImplementation;
	Logger log = Logger.getRootLogger();
	private ObjectMapper om = new ObjectMapper();
	
	public void AddUser(Users user) {
		ud.addUser(user);
	}

	@Override
	public Users getUserById(int id) {
		return ud.getUserById(id);
	}

	@Override
	public List<Users> getUsers() {
		return ud.getUsers();
	}

	@Override
	public boolean login(Credential cred, HttpSession httpSession) {
		Users u = ud.findByUsernameAndPassword(cred.getUsername(), cred.getPassword());
		if (u != null) {
			try {
				httpSession.setAttribute("user", om.writeValueAsString(u));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			httpSession.setAttribute("id", u.getUserId());
			return true;
		} 
		return false;
	}
}
