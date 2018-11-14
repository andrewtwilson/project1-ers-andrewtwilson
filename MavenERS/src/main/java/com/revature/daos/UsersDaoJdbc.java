package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.UserRoles;
import com.revature.model.Users;
import com.revature.util.ConnectionUtil;

public class UsersDaoJdbc implements UsersDao {
	private Logger log = Logger.getRootLogger();
	
//	private AppUser extractFromResultSet(ResultSet rs) throws SQLException {
//		return new AppUser(rs.getInt("user_id"), rs.getString("username"), null,
//				new UserRole(rs.getInt("role_id"), rs.getString("role_name")));
//	}

	public void addUser(Users user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ers_users (ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?) RETURNING ers_users_id;");

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRole().getId());

			ResultSet rs = ps.executeQuery();
			
			int userId=-1;
			if (rs.next()) {
				userId = rs.getInt("ers_users_id");
			} else {
				log.trace("Problem with user ID");
			}
			
			user.setUserId(userId);
			
			log.trace(user);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Users> getUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users " + 
					"INNER JOIN ers_user_roles " + 
					"ON ers_users.user_role_id=ers_user_roles.ers_user_role_id " + 
					"ORDER BY ers_users_id ASC;");
			
			ResultSet rs = ps.executeQuery();
			
			List<Users> usersList = new ArrayList<Users>();
			while(rs.next()) {
				usersList.add(new Users(rs.getInt("ers_users_id"), rs.getString("ers_username"), rs.getString("ers_password"), 
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), 
						new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role"))));
			}
			return usersList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users getUserById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users " + 
					"INNER JOIN ers_user_roles " + 
					"ON ers_users.user_role_id=ers_user_roles.ers_user_role_id " + 
					"WHERE ers_users_id=?;" );
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			Users user = null;
			if (rs.next()) {
				user = new Users(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"), 
						rs.getString("user_first_name"), 
						rs.getString("user_last_name"), 
						rs.getString("user_email"), 
						new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
			} else {
				log.debug("User does not exist");
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users " +
					"INNER JOIN ers_user_roles " + 
					"ON ers_users.user_role_id=ers_user_roles.ers_user_role_id " + 
					"WHERE ers_username = ? AND ers_password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			Users user = null;
			if (rs.next()) {
				user = new Users(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"), 
						rs.getString("user_first_name"), 
						rs.getString("user_last_name"), 
						rs.getString("user_email"), 
						new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
			} else {
				log.debug("User does not exist");
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
