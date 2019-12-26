package com.revature.repositories;

import java.util.List;

import com.revature.models.User;
import com.revature.models.UserRoles;

public interface UserDAO {
	public User findUser(int user_id);
	public User findUserByUsername(String user);
	public List<User> findAllUsers();
	public List<User> findUsersByRole(UserRoles role);
	public boolean createUser(User u);
	public boolean deleteUser(int user_id);
}
