package com.ss.lms.dao;

import org.springframework.stereotype.Component;

@Component
public class BorrowerDao extends DBConnection{
	private static BorrowerDao instance = null;

	private BorrowerDao() {
		// Exists only to defeat instantiation.
	}

	public static BorrowerDao getInstance() {
		if (instance == null) {
			instance = new BorrowerDao();
		}
		return instance;
	}
}
