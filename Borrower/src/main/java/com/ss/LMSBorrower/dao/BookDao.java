package com.ss.LMSBorrower.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Book;
import com.ss.LMSBorrower.Entity.LibraryBranch;

@Component
public class BookDao extends DBConnection{
	private static BookDao instance = null;

	private BookDao() {
		// Exists only to defeat instantiation.
	}

	public static BookDao getInstance() {
		if (instance == null) {
			instance = new BookDao();
		}
		return instance;
	}
	
	@Autowired
	AuthorDao authorDao;
	@Autowired
	PublisherDao publisherDao;
	
	public Book getBookById(int bookId) {
		String query ="select * from tbl_book where bookId=?";
		Book book = new Book();
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, bookId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(authorDao.getAuthorById(resultSet.getInt("authId")));
				book.setPublisher(publisherDao.getPublisherById(resultSet.getInt("pubId")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
		
	}
	
}
