package com.ss.LMSBorrower.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Borrower;

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
	
	public Borrower getBorrowerByCardNumber(int cardNumber) {
		String query = "select * from tbl_borrower where cardNo=?";
		Borrower borrower = new Borrower();
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(query);
			ps.setInt(1, cardNumber);
			ResultSet resultSet = ps.executeQuery(query);
			while(resultSet.next()) {
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setName(resultSet.getString("name"));
				borrower.setAddress(resultSet.getString("address"));
				borrower.setPhone(resultSet.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;
		
	}
}
