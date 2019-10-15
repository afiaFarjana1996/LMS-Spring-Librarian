package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.entity.BookCopies;

@Component
public class BookCopiesDao extends DBConnection{
	LibraryBranchDao libraryBranchDaoObj = LibraryBranchDao.getInstance();
	BookDao bookDaoObj = BookDao.getInstance();
	
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
	
	
	
	public List<BookCopies> getBookListByLibraryBranchId(int libraryBranchId) {
		String query1 = "select * from tbl_book_copies where branchId=?";
		
		List<BookCopies> bookCopyList = new ArrayList<>();
	    
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(query1);
			ps.setInt(1, libraryBranchId);
			ResultSet resultSet = ps.executeQuery();
			ResultSetMetaData rdms = resultSet.getMetaData();
	        int colNumber = rdms.getColumnCount();
	       
			while(resultSet.next()) {
				for(int i=1;i<=colNumber;i=i+3) {
					BookCopies bookCopies = new BookCopies(resultSet.getInt("noOfCopies"),
							bookDao.getBookById(resultSet.getInt("bookId")),
							libraryBranchDao.getLibraryBranchById(libraryBranchId));
					
					bookCopyList.add(bookCopies);
				}
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopyList;
	}
	
	
	
	
	
	public boolean checkIfCopiesExist(BookCopies bookCopies) {
		boolean retBool = false;
		String query = "select * from tbl_book_copies "
				+ "where bookId=? and branchId=?";
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(query);
			ps.setInt(1, bookCopies.getBook().getBookId());
			ps.setInt(2, bookCopies.getLibraryBranch().getBranchId());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				
					retBool = true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return retBool;
	}
	
	
	public void insertCopies(BookCopies bookCopies) {
		String query = "insert into tbl_book_copies "
				+ "values(?,?,?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			
			ps.setInt(1, bookCopies.getBook().getBookId());
			ps.setInt(2, bookCopies.getLibraryBranch().getBranchId());
			ps.setInt(3, bookCopies.getNoOfCopies());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateCopies(BookCopies bookCopies) {
		String query = "update tbl_book_copies set noOfCopies=?,"
				+ "where bookId=? and branchId=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
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
