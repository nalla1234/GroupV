package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
/*Created common connection class  */
public class ConnectSql {
	Connection connection = null;
	public Connection getconnectiondetail() {
	
		try {
			// Load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost : 3306/miniproject", "root",
					"sonu@12345678");

		} catch (Exception e) {

			System.out.println(e);
		}

		return connection;
	}

}
