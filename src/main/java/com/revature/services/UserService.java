package com.revature.services;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.models.UserRoles;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {
	private static UserDAO repository = new UserDAOImpl();
	
	public static User findUser(int user_id) {
		return repository.findUser(user_id);
	}
	
	public static List<User>  findAllUsers(){
		return repository.findAllUsers();
	}
	
	public static List<User> findUsersByRole(UserRoles role){
		return repository.findUsersByRole(role);
	}
	
	public static boolean createUser(User u) {
		u.setPassword(DigestUtils.sha256Hex(u.getPassword()));
		if(verifyUniqueUser(u.getUsername())) {
			return repository.createUser(u);
		} else {
			return false;
		}
	}
	
	public static boolean deleteUser(int user_id) {
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
	
	public static boolean verifyEmployee(String username) {
		List<User> list = repository.findUsersByRole(UserRoles.EMPLOYEE);
		if(!list.isEmpty()) {
			for(User u: list) {
				if(u.getUsername().equals(username)) {
					return true;
				}
			}
		}
		return false;
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

	public static UserDTO convertToDTO(User u) {
		// TODO Auto-generated method stub
		return new UserDTO(u.getUser_id(), 
							u.getUsername(), 
							u.getPassword(),
							u.getF_name(),
							u.getL_name(),
							u.getEmail(),
							u.getRole_id());
	}
}
