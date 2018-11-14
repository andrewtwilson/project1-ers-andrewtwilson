package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dto.Credential;
import com.revature.model.Users;

public interface UsersService {
	UsersService currentImplementation = new UsersServiceImpl();

	void AddUser(Users user);
	
	Users getUserById(int id);
	
	List<Users> getUsers();
	
	boolean login(Credential cred, HttpSession httpSession);
}
