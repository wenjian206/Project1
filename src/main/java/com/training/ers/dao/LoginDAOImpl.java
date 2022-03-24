package com.training.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.training.ers.model.User;
import com.training.pms.utility.DBConnection;

//CODE - DB
public class LoginDAOImpl implements LoginDAO {
	
	private static Logger logger = Logger.getLogger(LoginDAOImpl.class);
	Connection con = DBConnection.getConnection();
	
	@Override
	public boolean register(User user) {
		System.out.println("##Adding users: " + user);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = con.prepareStatement("insert into users values (default,?,?,?,?,?)");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getGender());
			statement.setString(4, user.getNotifications());
			statement.setString(5, user.getQualification());
			
			rows = statement.executeUpdate();		
			System.out.println(rows + " user registered successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean validate(String username, String password) {
		logger.info(username+ " is trying to login with password : "+password);

		boolean userValid = false;
		PreparedStatement stat;
		try {
			stat = con.prepareStatement("select * from users where username = ? and password = ?");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			userValid = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userValid;
	}

	@Override
	public List<User> getUsers() {
		System.out.println("##Printing all users  ");
		List<User> users = new ArrayList<User>();

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from users");
			while (res.next()) {
				User user = new User();
				user.setUserId(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setPassword(res.getString(3));
				user.setGender(res.getString(4));
				user.setNotifications(res.getString(5));
				user.setQualification(res.getString(6));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> getUsersByUserName(String username) {
		System.out.println("##Printing search users  ");
		List<User> users = new ArrayList<User>();

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from users where username = '"+username+"'");
			while (res.next()) {
				User user = new User();
				user.setUserId(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setPassword(res.getString(3));
				user.setGender(res.getString(4));
				user.setNotifications(res.getString(5));
				user.setQualification(res.getString(6));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	

}
