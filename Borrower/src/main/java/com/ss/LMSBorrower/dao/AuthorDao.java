package com.ss.LMSBorrower.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;


@Component
public class AuthorDao extends DBConnection{
	
	private static AuthorDao instance = null;

	private AuthorDao() {
		// Exists only to defeat instantiation.
	}

	public static AuthorDao getInstance() {
		if (instance == null) {
			instance = new AuthorDao();
		}
		return instance;
	}

	public com.ss.LMSBorrower.Entity.Author getAuthorById(int authorId) {
		
		String query = "select * from tbl_author where authorId=?";
		com.ss.LMSBorrower.Entity.Author author = new com.ss.LMSBorrower.Entity.Author();
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, authorId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				author.setId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return author;
	}
}
