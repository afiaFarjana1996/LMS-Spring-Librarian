package com.ss.LMSBorrower.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.BookCopies;

@Component
public class BookCopiesDao extends DBConnection{
	
	private static BookCopiesDao instance = null;

	private BookCopiesDao() {
		// Exists only to defeat instantiation.
	}

	public static BookCopiesDao getInstance() {
		if (instance == null) {
			instance = new BookCopiesDao();
		}
		return instance;
	}
	
	@Autowired
	LibraryBranchDao libraryBranchDao;
	@Autowired
	BookDao bookDao;
	
	public BookCopies getBookCopiesInformation(int branchId, int bookId) {
		String query = "select * from tbl_book_copies where branchId=? and bookId=?";
		BookCopies bookCopies = new BookCopies();
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(query);
			ps.setInt(1, branchId);
			ps.setInt(2, bookId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				bookCopies.setBook(bookDao.getBookById(resultSet.getInt("bookId")));
				bookCopies.setLibraryBranch(libraryBranchDao.getLibraryBranchById(resultSet.getInt("branchId")));
				bookCopies.setNoOfCopies(resultSet.getInt("noOfCopies"));
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return bookCopies;
	}
	
	public void updateNoOfBookCopies(BookCopies bookCopies) {
		String query = "update tbl_book_copies set noOfCopies=? where " + 
						"bookId=? and branchId=?";
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(query);
			ps.setInt(1, bookCopies.getNoOfCopies());
			ps.setInt(2, bookCopies.getBook().getBookId());
			ps.setInt(3, bookCopies.getLibraryBranch().getBranchId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
