package com.ss.LMSBorrower.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Publisher;

@Component
public class PublisherDao extends DBConnection{
	private static PublisherDao instance = null;

	private PublisherDao() {
		// Exists only to defeat instantiation.
	}

	public static PublisherDao getInstance() {
		if (instance == null) {
			instance = new PublisherDao();
		}
		return instance;
	}
	
	public Publisher getPublisherById(int publisherId) {
		String query = "select * from tbl_publisher where publisherId=?";
		Publisher publisher = new Publisher();
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, publisherId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				publisher.setPublisherId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisher;
	}

}
