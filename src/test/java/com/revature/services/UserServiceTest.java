package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.User;
import com.revature.repositories.ReimbursementDAOImpl;
import com.revature.repositories.UserDAOImpl;

public class UserServiceTest {
	
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
	public void testFindCorrectUserById() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertEquals(u, mockUserService.findUser(1));
	}
	
	@Test
	public void testFindWrongUserById() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		assertNotEquals(u, mockUserService.findUser(2));
	}
	
	@Test 
	public void testFindNoUserById() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertNull(mockUserService.findUser(2));
	}
	
	@Test
	public void testFindCorrectUserByName() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertEquals(u, mockUserService.findUserByUsername("MockUser"));
	}
	
	@Test
	public void testFindWrongUserByName() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		assertNotEquals(u, mockUserService.findUserByUsername("MockOtherUser"));
	}
	
	@Test 
	public void testFindNoUserByName() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertNull(mockUserService.findUserByUsername("MockOtherUser"));
	}
	
	@Test
	public void testVerifyCorrectUser() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertEquals(u, mockUserService.verifyUser("MockUser", "mockpw"));
	}
	
	@Test
	public void testVerifyUserWrongUsername() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		assertNull(mockUserService.verifyUser("MockOtherUser", "mockpw"));
	}
	
	@Test 
	public void testVerifyUserWrongPassword() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		assertNull(mockUserService.verifyUser("MockUser", "differentpw"));
	}
	
	@Test
	public void testFindAllUsers() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		User u3 = new User(3, "NewPhone", "whodis", "Slim", "Shady", "email@reddit.com", 1);
		List<User> list = new ArrayList<>();
		list.add(u);
		list.add(u2);
		list.add(u3);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		when(mockUserDAO.findUserByUsername("NewPhone")).thenReturn(u3);
		assertEquals(list, mockUserService.findAllUsers());
	}
	
	@Test
	public void testFoundIncorrectUsers() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		User u3 = new User(3, "NewPhone", "whodis", "Slim", "Shady", "email@reddit.com", 1);
		List<User> list = new ArrayList<>();
		list.add(u);
		list.add(u2);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		when(mockUserDAO.findUserByUsername("NewPhone")).thenReturn(u3);
		assertNotEquals(list, mockUserService.findAllUsers());
	}
	
	@Test
	public void testVerifyEmployeeTrue() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		//User u3 = new User(3, "NewPhone", "whodis", "Slim", "Shady", "email@reddit.com", 1);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		//when(mockUserDAO.findUserByUsername("NewPhone")).thenReturn(u3);
		assertTrue(mockUserService.verifyEmployee(u.getUsername()));
	}
	
	@Test
	public void testVerifyNotEmployee() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		User u2 = new User(2, "MockOtherUser", "differentpw", "Alien", "Character", "historychannel@gmail.com", 2);
		when(mockUserDAO.findUserByUsername("MockUser")).thenReturn(u);
		when(mockUserDAO.findUserByUsername("MockOtherUser")).thenReturn(u2);
		assertFalse(mockUserService.verifyEmployee(u2.getUsername()));
	}
	
	@Test
	public void testSuccessfulCreateUser() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockName")).thenReturn(null);
		when(mockUserDAO.createUser(u)).thenReturn(true);
		assertTrue(mockUserService.createUser(u));
	}
	
	@Test
	public void testFailedCreateUser() {
		User u = new User(1, "MockUser", "mockpw", "Human", "Person", "mockUser@gmail.com", 1);
		when(mockUserDAO.findUserByUsername("MockName")).thenReturn(null);
		when(mockUserDAO.createUser(u)).thenReturn(false);
		assertFalse(mockUserService.createUser(u));
	}
	
}
