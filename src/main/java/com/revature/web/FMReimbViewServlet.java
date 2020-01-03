package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.services.ReimbursementService;

public class FMReimbViewServlet extends HttpServlet{

	private static Logger logger = LogManager.getLogger(ReimbReviewServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession();
		try{
			String username = session.getAttribute("username").toString();
			//int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
			logger.info("User " + username + " is attempting to view reimbursements");
			List<Reimbursement> list = ReimbursementService.findAllReimbursements();
			if(list.isEmpty()) {
				logger.warn("User " + username + " does not have any reimbursements");	
				res.setContentType("application/json");
				res.setStatus(204);
			} else {
				PrintWriter out = res.getWriter();
				res.setContentType("application/json");
				List<ReimbursementDTO> dtoList = new ArrayList<>();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				for(Reimbursement re: list) {
					dtoList.add(new ReimbursementDTO(re.getReimb_id(), 
														re.getAmount(),
														re.getSubmitted().format(formatter),
														re.getResolved().format(formatter),
														re.getReceipt(),
														re.getDesc(),
														re.getAuthor_id(),
														re.getResolver_id(),
														ReimbursementStatus.valueOf(re.getStatus_id()).toString(),
														ReimbursementType.valueOf(re.getType_id()).toString()));
					//System.out.println(re.getDesc());
				}
				
				out.println(om.writeValueAsString(dtoList));
			}
		} catch (NumberFormatException e) {	
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}