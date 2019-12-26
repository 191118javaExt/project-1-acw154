package com.revature.services;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {
	private static UserDAO repository = new UserDAOImpl();
	
	public User findUser(int user_id) {
		return repository.findUser(user_id);
	}
	
	public List<User> findAllUsers(){
		return repository.findAllUsers();
	}
	
	public List<User> findUsersByRole(UserRoles role){
		return repository.findUsersByRole(role);
	}
	
	public boolean createUser(User u) {
		u.setPassword(DigestUtils.sha256Hex(u.getPassword()));
		return repository.createUser(u);
	}
	
	public boolean deleteUser(int user_id) {
		return repository.deleteUser(user_id);
	}
	
	public static User verifyUser(String username, String password) {
		User found = repository.findUserByUsername(username);
		if(found != null) {
			String hex_password = DigestUtils.sha256Hex(password);
			if(found.getUsername().equals(username) && found.getPassword().equals(hex_password)) {
				return found;
			}
		}
		return null;
	}
	
	public static boolean verifyUniqueUser(String username) {
		if(repository.findUserByUsername(username) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static User findUserByUsername(String user) {
		return repository.findUserByUsername(user);
	}
}
