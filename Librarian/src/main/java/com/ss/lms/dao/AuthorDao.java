package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ss.lms.entity.Author;

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

	public Author getAuthorById(int authorId) {
		
		String query = "select * from tbl_author where authorId=?";
		Author author = new Author();
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
