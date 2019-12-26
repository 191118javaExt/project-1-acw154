package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public User findUser(int user_id) {
		User u = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_users WHERE ers_users_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,  user_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int u_id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String f_name = rs.getString("user_first_name");
				String l_name = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");
				u = new User(u_id, username, password, f_name, l_name, email, role_id);
			}
		} catch (SQLException e) {
			logger.warn("Unable to Find User " + user_id + " " + e.getMessage());
		}
		return u;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_users;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int u_id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String f_name = rs.getString("user_first_name");
				String l_name = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");
				list.add(new User(u_id, username, password, f_name, l_name, email, role_id));
			}
		} catch (SQLException e) {
			logger.warn("Unable to Find All Users " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<User> findUsersByRole(UserRoles role) {
		List<User> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_users WHERE user_role_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, role.getValue());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int u_id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String f_name = rs.getString("user_first_name");
				String l_name = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");
				list.add(new User(u_id, username, password, f_name, l_name, email, role_id));
			}
		} catch (SQLException e) {
			logger.warn("Unable to Find Users By Role " + role.toString() + " " + e.getMessage());
		}
		return list;
	}

	@Override
	public boolean createUser(User u) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project1.ers_users" + 
					" (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)" + 
					" VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getF_name());
			stmt.setString(4, u.getL_name());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole_id());
			if(!stmt.execute()) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Create User " + e.getMessage());
		}
		return false;		
	}

	@Override
	public boolean deleteUser(int user_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "DELETE FROM project1.ers_users WHERE ers_users_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, user_id);
			if(!stmt.execute()) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Delete User " + user_id + " " + e.getMessage());
		}
		return false;		

	}

	@Override
	public User findUserByUsername(String user) {
		User u = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_users WHERE ers_username = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int u_id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String f_name = rs.getString("user_first_name");
				String l_name = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");
				u = new User(u_id, username, password, f_name, l_name, email, role_id);
			}
		} catch (SQLException e) {
			logger.warn("Unable to Find User " + user + " " + e.getMessage());
		}
		return u;
	}

}
