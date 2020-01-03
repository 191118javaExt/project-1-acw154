package com.revature.web;

import java.io.BufferedReader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.ReviewTemplate;
import com.revature.models.SubmitTemplate;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbReviewServlet extends HttpServlet{
	
	private static Logger logger = LogManager.getLogger(ReimbReviewServlet.class);
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
		ReviewTemplate reviewAttempt = om.readValue(body, ReviewTemplate.class);
		
		try{
			int reimb_id = Integer.parseInt(reviewAttempt.getReimb_id());
			ReimbursementStatus status = ReimbursementStatus.valueOf(reviewAttempt.getReimb_status());
			HttpSession session = req.getSession();
			int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
			if(ReimbursementService.findReimbursement(reimb_id) == null) {
				logger.warn("Reimbursement does not exist");
				res.setContentType("application/json");
				res.setStatus(204);
			} else {
				switch(status) {
					case APPROVED: {
						if(ReimbursementService.approveReimbursement(reimb_id, user_id)){
							logger.info("Approved Reimbursement " + reimb_id);	
							res.setContentType("application/json");
							res.setStatus(200);
						} else {
							logger.warn("Reimbursement could not be approved");	
							res.setContentType("application/json");
							res.setStatus(204);
						}
						break;
					}
					
					case DENIED: {
						if(ReimbursementService.denyReimbursement(reimb_id, user_id)) {
							logger.info("Denied Reimbursement " + reimb_id);	
							res.setContentType("application/json");
							res.setStatus(200);
						} else {
							logger.warn("Reimbursement could not be denied");	
							res.setContentType("application/json");
							res.setStatus(204);
						}
						break;
					}
				case PENDING:
					break;
				default:
					break;
				}
			}
		} catch (NumberFormatException e) {
			logger.warn("Could not convert user_id string to integer");	
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}