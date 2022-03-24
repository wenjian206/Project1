package com.training.ers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.training.ers.dao.LoginDAO;
import com.training.ers.dao.LoginDAOImpl;
import com.training.ers.model.User;

class LoginDAOImplTest {
	
	LoginDAO loginDAO;
	User user;
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		loginDAO = new LoginDAOImpl();
		user = new User();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetUsers() {
		List <User> users = loginDAO.getUsers();
		assertNotEquals(0,users.size());
	}

}
