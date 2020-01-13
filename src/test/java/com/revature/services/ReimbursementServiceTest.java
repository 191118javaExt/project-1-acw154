package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDAOImpl;
import com.revature.repositories.UserDAOImpl;

public class ReimbursementServiceTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}
	
	UserDAOImpl mockUserDAO = mock(UserDAOImpl.class);
	UserService mockUserService = new UserService(mockUserDAO);
	ReimbursementDAOImpl mockReimbDAO = mock(ReimbursementDAOImpl.class);
	ReimbursementService mockReimbService = new ReimbursementService(mockReimbDAO);
	
	@Test
	public void testFindCompleteReimbursements() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		byte[] b2 = {5, 4, 3, 2, 1};
		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
		List<Reimbursement> list = new ArrayList<>();
		list.add(re1);
		list.add(re2);
		when(mockReimbDAO.findAllReimbursements()).thenReturn(list);
		assertEquals(list, mockReimbService.findAllReimbursements());
	}
	
//	@Ignore
//	@Test 
//	public void testFindIncompleteReimbursements() {
//		byte[] b1 = {0, 1, 2, 3, 4};
//		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
//		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
//		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
//		byte[] b2 = {5, 4, 3, 2, 1};
//		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
//		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
//		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
//		List<Reimbursement> list = new ArrayList<>();
//		list.add(re1);
//		//list.add(re2);
//		when(mockReimbDAO.findAllReimbursements()).thenReturn(list);
//		assertNotEquals(list, mockReimbService.findAllReimbursements());
//	}
	
	@Test 
	public void testSubmitReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.submitReimbursement(re1)).thenReturn(true);
		assertTrue(mockReimbService.submitReimbursement(re1));
	}
	
	@Test
	public void testSubmitReimbursementFailed() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.submitReimbursement(re1)).thenReturn(false);
		assertFalse(mockReimbService.submitReimbursement(re1));
	}
	
	@Test
	public void testApproveReimbursementSuccess() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.approveReimbursement(1, 1)).thenReturn(true);
		assertTrue(mockReimbService.approveReimbursement(1,1));
	}
	
	@Test
	public void testApproveReimbursementFailed() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.approveReimbursement(1, 1)).thenReturn(false);
		assertFalse(mockReimbService.approveReimbursement(1,1));
	}
	
	@Test
	public void testApproveNonexistentReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.approveReimbursement(1, 3)).thenReturn(false);
		assertFalse(mockReimbService.approveReimbursement(1,3));
	}
	
	@Test
	public void testDenyReimbursementSuccess() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.denyReimbursement(1, 1)).thenReturn(true);
		assertTrue(mockReimbService.denyReimbursement(1,1));
	}
	
	@Test
	public void testDenyReimbursementFailed() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.denyReimbursement(1, 1)).thenReturn(false);
		assertFalse(mockReimbService.denyReimbursement(1,1));
	}
	
	@Test
	public void testDenyNonexistentReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.denyReimbursement(1, 3)).thenReturn(false);
		assertFalse(mockReimbService.denyReimbursement(1,3));
	}
	
	@Test
	public void testFindCorrectReimbursementsByAuthor() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		byte[] b2 = {5, 4, 3, 2, 1};
		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
		byte[] b3 = {2, 4, 6, 8, 0};
		LocalDateTime sub3 = LocalDateTime.of(2019, 12, 22, 16, 20);
		LocalDateTime res3 = LocalDateTime.of(2020, 1, 4, 9, 0);
		Reimbursement re3 = new Reimbursement(3, 44.88, sub3, res3, b3, "Work Lunch", 2, 3, 1, 1);
		List<Reimbursement> list = new ArrayList<>();
		list.add(re1);
		list.add(re2);
		when(mockReimbDAO.findReimbursementsByAuthor(1)).thenReturn(list);
		assertEquals(list, mockReimbService.findReimbursementsByAuthor(1));
	}
	
//	@Ignore
//	@Test
//	public void testFindIncorrectReimbursementsByAuthor() {
//		byte[] b1 = {0, 1, 2, 3, 4};
//		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
//		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
//		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
//		byte[] b2 = {5, 4, 3, 2, 1};
//		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
//		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
//		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
//		byte[] b3 = {2, 4, 6, 8, 0};
//		LocalDateTime sub3 = LocalDateTime.of(2019, 12, 22, 16, 20);
//		LocalDateTime res3 = LocalDateTime.of(2020, 1, 4, 9, 0);
//		Reimbursement re3 = new Reimbursement(3, 44.88, sub3, res3, b3, "Work Lunch", 2, 3, 1, 1);
//		List<Reimbursement> list = new ArrayList<>();
//		list.add(re1);
//		list.add(re2);
//		list.add(re3);
//		when(mockReimbDAO.findReimbursementsByAuthor(1)).thenReturn(list);
//		assertNotEquals(list, mockReimbService.findReimbursementsByAuthor(1));
//	}
	
	@Test
	public void testNoReimbursementFromNonexistentAuthor() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		byte[] b2 = {5, 4, 3, 2, 1};
		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
		byte[] b3 = {2, 4, 6, 8, 0};
		LocalDateTime sub3 = LocalDateTime.of(2019, 12, 22, 16, 20);
		LocalDateTime res3 = LocalDateTime.of(2020, 1, 4, 9, 0);
		Reimbursement re3 = new Reimbursement(3, 44.88, sub3, res3, b3, "Work Lunch", 2, 3, 1, 1);
		List<Reimbursement> list = new ArrayList<>();
		when(mockReimbDAO.findReimbursementsByAuthor(3)).thenReturn(list);
		assertEquals(list, mockReimbService.findReimbursementsByAuthor(3));
	}
	
	@Test
	public void findSingleReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.findReimbursement(1)).thenReturn(re1);
		assertEquals(re1, mockReimbService.findReimbursement(1));
	}
	
	@Test
	public void findWrongReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		byte[] b2 = {5, 4, 3, 2, 1};
		LocalDateTime sub2 = LocalDateTime.of(2020, 3, 1, 8, 30);
		LocalDateTime res2 = LocalDateTime.of(2020, 3, 6, 15, 45);
		Reimbursement re2 = new Reimbursement(2, 315.22, sub2, res2, b2, "Work Supplies", 1, 2, -1, 2);
		when(mockReimbDAO.findReimbursement(1)).thenReturn(re1);
		assertNotEquals(re1, mockReimbService.findReimbursement(2));
	}
	
	@Test
	public void findNonexistentReimbursement() {
		byte[] b1 = {0, 1, 2, 3, 4};
		LocalDateTime sub1 = LocalDateTime.of(2020, 1, 1, 10, 30);
		LocalDateTime res1 = LocalDateTime.of(2020, 1, 11, 0, 0);
		Reimbursement re1 = new Reimbursement(1, 30.55, sub1, res1, b1, "Used Company Funds", 1, 2, 1, 1);
		when(mockReimbDAO.findReimbursement(1)).thenReturn(re1);
		assertNull(mockReimbService.findReimbursement(3));
	}
}
