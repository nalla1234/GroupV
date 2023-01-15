package com.miniproject.ecommerce;
/* This class is used provide exception for "No stock for the product if quantity is '0' in database "*/

public class Stock extends RuntimeException {

	public Stock(String message) {
		super(message);
	}

}
