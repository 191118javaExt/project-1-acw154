package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repositories.UserDAOImpl;
import com.revature.services.UserService;

public class FMLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(FMLoginServlet.class);
	private static ObjectMapper om = new ObjectMapper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
BufferedReader reader = req.getReader();
		UserService us = new UserService(new UserDAOImpl());
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();
		
		logger.info("User attempted to login with username " + username);
		User u = us.verifyUser(username, password);
		if(u != null && !us.verifyEmployee(u.getUsername())) {
			HttpSession session = req.getSession();
			// Gets the current session, or creates one if it did not exist
			session.setAttribute("username", username);
			session.setAttribute("user_id", u.getUser_id());
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			UserDTO uDTO = UserService.convertToDTO(u);
			out.println(om.writeValueAsString(uDTO));
			logger.info("FM " + username + " has successfully logged in");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}