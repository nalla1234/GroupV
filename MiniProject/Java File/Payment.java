package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

public class Payment {
	ConnectSql connectsql = new ConnectSql();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	int amount;
	
	/*It will calculate  the amount to bew plaid by the user by quantity and by  fetching the price of that  product from table 'productlist' */

	public int payment(Map<Integer, Integer> items) {
		int amount = 0;
		int quantity = 0, price = 0;

		Set<Integer> set = items.keySet();

		for (int j : set) {
			try {
				connection = connectsql.getconnectiondetail();
				preparedStatement = connection
						.prepareStatement("select Price, Name from productlist where Productid = ?");
				preparedStatement.setInt(1, j);

				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					price = resultSet.getInt(1);
					quantity = items.get(j);
					amount = amount + price * quantity;
				}

			} catch (Exception e) {

				System.out.println(e);

			}

		}

		return amount;

	}

}
