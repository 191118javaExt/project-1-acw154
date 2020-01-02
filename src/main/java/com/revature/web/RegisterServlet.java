package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterTemplate;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.models.UserRoles;
import com.revature.services.UserService;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RegisterServlet.class);
	private static ObjectMapper om = new ObjectMapper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		RegisterTemplate registerAttempt = om.readValue(body, RegisterTemplate.class);
		String username = registerAttempt.getUsername();
		//String password = registerAttempt.getPassword();
		
		logger.info("User attempted to register with username " + username);
		User u = UserService.findUserByUsername(username);
		if(u == null) {
			HttpSession session = req.getSession();
			if(UserService.createUser(new User(registerAttempt.getUsername(),
											registerAttempt.getPassword(),
											registerAttempt.getFname(),
											registerAttempt.getLname(),
											registerAttempt.getEmail(),
											UserRoles.valueOf(registerAttempt.getRole()).getValue()))) {
				logger.info("User has been registered with username " + registerAttempt.getUsername() + " and password "
						+ registerAttempt.getPassword());
			} else {
				res.setContentType("application/json");
				res.setStatus(204);
			}
			// Gets the current session, or creates one if it did not exist
			//session.setAttribute("username", username);
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}
