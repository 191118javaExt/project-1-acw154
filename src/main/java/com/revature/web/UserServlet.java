package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repositories.UserDAOImpl;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		UserService us = new UserService(new UserDAOImpl());
		res.setContentType("application/json");
		List<User> all = us.findAllUsers();
		List<UserDTO> allDTO = new ArrayList<>();
		
		for(User u : all) {
			allDTO.add(new UserDTO(u.getUser_id(),
					u.getUsername(),
					u.getPassword(),
					u.getF_name(),
					u.getL_name(),
					u.getEmail(),
					u.getRole_id()));
		}
		
		String json = om.writeValueAsString(all);
//		LoginTemplate login = om.readValue("{ .. }", LoginTemplate.class);
		
		PrintWriter out = res.getWriter();
		out.println(json);
	}
}
