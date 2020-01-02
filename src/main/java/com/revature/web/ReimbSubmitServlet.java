package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.SubmitTemplate;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbSubmitServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	private static final long serialVersionUID = 1L;
	
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
		SubmitTemplate submitTemplate = om.readValue(body, SubmitTemplate.class);
		
		try{
			Double amount = Double.parseDouble(submitTemplate.getAmount());
			String description = submitTemplate.getDescription();
			String reimb_type = submitTemplate.getReimb_type();
			//TODO: Figure out how to deal with receipts
			//String receipt = submitTemplate.getReceipt();
			logger.info("User attempted to submit Reimbursement of " + amount + " dollars and type " + reimb_type);
			HttpSession session = req.getSession();
			int user_id =  Integer.parseInt(session.getAttribute("user_id").toString());
			Reimbursement reimb = new Reimbursement(amount, description, user_id, ReimbursementStatus.PENDING.getValue(), ReimbursementType.valueOf(reimb_type).getValue());
			if(ReimbursementService.submitReimbursement(reimb)) {
				logger.info("Reimbursement created");
				res.setContentType("application/json");
				res.setStatus(200);
			} else {
				res.setContentType("application/json");
				res.setStatus(204);
			}
		} catch (NumberFormatException e) {
			logger.warn("Could not convert user_id string to integer");	
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}
	

