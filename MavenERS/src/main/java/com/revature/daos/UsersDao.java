package com.revature.daos;

import java.util.List;

import com.revature.model.Users;

public interface UsersDao {
	UsersDao currentImplementation = new UsersDaoJdbc();

	void addUser(Users user);
	
	Users getUserById(int id);
	
	List<Users> getUsers(); 
	
	Users findByUsernameAndPassword(String username, String password);
}
