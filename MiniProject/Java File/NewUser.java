package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NewUser {

	Scanner scanner = new Scanner(System.in);
	ConnectSql connect = new ConnectSql();
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet rs = null;

	private String name, email, password;

	int i;

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public NewUser newregistration() {

		/* Taking input for the following fields username, password, password from new user*/
		
		
		NewUser newUser = new NewUser();
		System.out.println("Enter your username");
		String uname = scanner.next();
		newUser.setName(uname);

		System.out.println("Enter your email");
		String uemail = scanner.next();
		newUser.setEmail(uemail);

		System.out.println("Set your password");
		String upassword = scanner.next();
		newUser.setPassword(upassword);

		newUser.getuserinformation(newUser);

		return newUser;

	}

	private void getuserinformation(NewUser newUser) {

		/* Updating the information of the user on database under table name 'userdetails' */
		
		
		try {
			connection = connect.getconnectiondetail();
			preparedstatement = connection
					.prepareStatement("insert into userdetails(Username,Email, Password)values(?,?,?)");
			preparedstatement.setString(1, newUser.getName());
			preparedstatement.setString(2, newUser.getEmail());
			preparedstatement.setString(3, newUser.getPassword());

			i = preparedstatement.executeUpdate();
			System.out.println("The user is registrated\n" + i);
		} catch (Exception e) {

			System.out.println(e);

		}

	}

//	public void existinguser(String user, String password) throws SQLException {
//		String euser, eemail, epassword;
//		connection = connect.getconnectiondetail();
//		preparedstatement = connection
//				.prepareStatement("select  username,email, password from userdetails where user = ? and password = ?");
//		preparedstatement.setString(1, user);
//		preparedstatement.setString(2, password);
//		rs = preparedstatement.executeQuery();
//
//		while (rs.next()) {
//			euser = rs.getString(2);
//			eemail = rs.getString(3);
//			epassword = rs.getString(4);
//
//		}
//
//	}

}
