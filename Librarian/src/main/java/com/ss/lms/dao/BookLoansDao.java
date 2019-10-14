package com.ss.lms.dao;

import org.springframework.stereotype.Component;

@Component
public class BookLoansDao extends DBConnection{
	
	private static BookLoansDao instance = null;

	private BookLoansDao() {
		// Exists only to defeat instantiation.
	}

	public static BookLoansDao getInstance() {
		if (instance == null) {
			instance = new BookLoansDao();
		}
		return instance;
	}

}
