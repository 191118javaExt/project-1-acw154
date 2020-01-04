package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	private static Logger logger = LogManager.getLogger(ReimbursementDAOImpl.class);

	@Override
	public Reimbursement findReimbursement(int reimb_id) {
		Reimbursement r = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_reimbursement WHERE reimb_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, reimb_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int r_id = rs.getInt("reimb_id");
				double amt = rs.getDouble("reimb_amount");
				Timestamp ts_submitted = rs.getTimestamp("reimb_submitted");
				LocalDateTime ldt_submitted;
				if(ts_submitted != null) {
					ldt_submitted = ts_submitted.toLocalDateTime();
				} else {
					ldt_submitted = null;
				}
				Timestamp ts_resolved = rs.getTimestamp("reimb_resolved");
				LocalDateTime ldt_resolved;
				if(ts_resolved != null) {
					ldt_resolved = ts_resolved.toLocalDateTime();
				} else {
					ldt_resolved = null;
				}
				String desc = rs.getString("reimb_description");
				byte[] b = rs.getBytes("reimb_receipt");
				if(rs.wasNull()) {
					b = null;
				}
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				r = new Reimbursement(r_id, amt, ldt_submitted, ldt_resolved, b, desc, author_id, resolver_id, status_id, type_id);
			}
		} catch (SQLException e) {
			logger.warn("Unable to retrieve Reimbursement " + reimb_id +" "+ e.getMessage());
		}
		return r;
	}

	@Override
	public List<Reimbursement> findReimbursementsByStatus(ReimbursementStatus status) {
		List<Reimbursement> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_reimbursement WHERE reimb_status = (?) ORDER BY reimb_submitted DESC;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, status.getValue());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int r_id = rs.getInt("reimb_id");
				double amt = rs.getDouble("reimb_amount");
				Timestamp ts_submitted = rs.getTimestamp("reimb_submitted");
				LocalDateTime ldt_submitted;
				if(ts_submitted != null) {
					ldt_submitted = ts_submitted.toLocalDateTime();
				} else {
					ldt_submitted = null;
				}
				Timestamp ts_resolved = rs.getTimestamp("reimb_resolved");
				LocalDateTime ldt_resolved;
				if(ts_resolved != null) {
					ldt_resolved = ts_resolved.toLocalDateTime();
				} else {
					ldt_resolved = null;
				}
				String desc = rs.getString("reimb_description");
				byte[] b = rs.getBytes("reimb_receipt");
				if(rs.wasNull()) {
					b = null;
				}
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				list.add(new Reimbursement(r_id, amt, ldt_submitted, ldt_resolved, b, desc, author_id, resolver_id, status_id, type_id));
			}
		} catch (SQLException e) {
			logger.warn("Unable to retrieve Reimbursements of Status: " + status.toString() + " " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Reimbursement> findAllReimbursements() {
		List<Reimbursement> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_reimbursement ORDER BY reimb_submitted DESC;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int r_id = rs.getInt("reimb_id");
				double amt = rs.getDouble("reimb_amount");
				Timestamp ts_submitted = rs.getTimestamp("reimb_submitted");
				LocalDateTime ldt_submitted;
				if(ts_submitted != null) {
					ldt_submitted = ts_submitted.toLocalDateTime();
				} else {
					ldt_submitted = null;
				}
				Timestamp ts_resolved = rs.getTimestamp("reimb_resolved");
				LocalDateTime ldt_resolved;
				if(ts_resolved != null) {
					ldt_resolved = ts_resolved.toLocalDateTime();
				} else {
					ldt_resolved = null;
				}
				String desc = rs.getString("reimb_description");
				byte[] b = rs.getBytes("reimb_receipt");
				if(rs.wasNull()) {
					b = null;
				}
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				list.add(new Reimbursement(r_id, amt, ldt_submitted, ldt_resolved, b, desc, author_id, resolver_id, status_id, type_id));
			}
		} catch (SQLException e) {
			logger.warn("Unable to retrieve Reimbursements " + e.getMessage());
		}
		return list;
	}

	@Override
	public boolean submitReimbursement(Reimbursement reimb) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project1.ers_reimbursement" + 
					" (reimb_amount, reimb_description, reimb_receipt, reimb_author, reimb_status_id, reimb_type_id)" + 
					" VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDouble(1, reimb.getAmount());
			//stmt.setTimestamp(2, Timestamp.valueOf(reimb.getSubmitted()));
			//stmt.setTimestamp(3, Timestamp.valueOf(reimb.getResolved()));
			stmt.setString(2, reimb.getDesc());
			stmt.setBytes(3, reimb.getReceipt());
			stmt.setInt(4, reimb.getAuthor_id());
			//stmt.setInt(7, reimb.getResolver_id());
			stmt.setInt(5, 0);
			stmt.setInt(6, reimb.getType_id());
			if(!stmt.execute()){
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Submit Reimbursement " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteReimbursement(int reimb_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "DELETE FROM project1.ers_reimbursement WHERE reimb_id= (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, reimb_id);
			if(!stmt.execute()){
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Delete Reimbursement " + e.getMessage());
		}
		return false;
	}

	@Override
	public List<Reimbursement> findReimbursementsByAuthor(int a_id) {
		List<Reimbursement> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project1.ers_reimbursement WHERE reimb_author = (?) ORDER BY reimb_submitted DESC;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, a_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int r_id = rs.getInt("reimb_id");
				double amt = rs.getDouble("reimb_amount");
				Timestamp ts_submitted = rs.getTimestamp("reimb_submitted");
				LocalDateTime ldt_submitted;
				if(ts_submitted != null) {
					ldt_submitted = ts_submitted.toLocalDateTime();
				} else {
					ldt_submitted = null;
				}
				Timestamp ts_resolved = rs.getTimestamp("reimb_resolved");
				LocalDateTime ldt_resolved;
				if(ts_resolved != null) {
					ldt_resolved = ts_resolved.toLocalDateTime();
				} else {
					ldt_resolved = null;
				}
				String desc = rs.getString("reimb_description");
				byte[] b = rs.getBytes("reimb_receipt");
				if(rs.wasNull()) {
					b = null;
				}
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				list.add(new Reimbursement(r_id, amt, ldt_submitted, ldt_resolved, b, desc, author_id, resolver_id, status_id, type_id));
			}
		} catch (SQLException e) {
			logger.warn("Unable to Retrieve Reimbursements by Author " + e.getMessage());
		}
		return list;
	}

	@Override
	public boolean approveReimbursement(int reimb_id, int resolvr_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project1.ers_reimbursement SET reimb_status_id = 1, reimb_resolver = (?) WHERE reimb_id= (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, resolvr_id);
			stmt.setInt(2, reimb_id);
			if(!stmt.execute()){
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Approve Reimbursement " + e.getMessage());
		}
		return false;

	}

	@Override
	public boolean denyReimbursement(int reimb_id, int resolvr_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project1.ers_reimbursement SET reimb_status_id = -1, reimb_resolver = (?) WHERE reimb_id= (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, resolvr_id);
			stmt.setInt(2, reimb_id);
			if(!stmt.execute()){
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Unable to Approve Reimbursement " + e.getMessage());
		}
		return false;

	}
	
}
