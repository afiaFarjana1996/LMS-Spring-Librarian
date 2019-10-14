package com.ss.LMSBorrower.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Book;
import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.Entity.Borrower;
import com.ss.LMSBorrower.Entity.LibraryBranch;

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
	
	@Autowired
	BookDao bookDao;
	@Autowired
	LibraryBranchDao libraryBranchDao;
	@Autowired 
	BorrowerDao borrowerDao;
	
	public boolean checkIfAlreadyCheckedOut(Borrower borrower, LibraryBranch libraryBranch, Book book) {
		String query = "select * from tbl_book_loans where bookId=? and branchId=? and cardNo=?";
		
		Boolean retBool = false;
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, book.getBookId());
			ps.setInt(2, libraryBranch.getBranchId());
			ps.setInt(3, borrower.getCardNo());
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
	
	
	public void insertIntoBookLoans(BookLoans bookLoans) {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Statement st = getConnection().createStatement();
			String query = "insert into tbl_book_loans values("+bookLoans.getBook().getBookId()
			+","+bookLoans.getLibraryBranch().getBranchId()
			+","+ bookLoans.getBorrower().getCardNo()
			+",'"+formatter.format(bookLoans.getDateOut()) 
			+"','"+formatter.format(bookLoans.getDueDate())+"');";
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BookLoans> getListOfLoanedBooks(int cardNumber){
		String query = "SELECT * FROM tbl_book_loans where cardNo=?";
		List<BookLoans> loanedBook = new ArrayList<>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, cardNumber);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				BookLoans bookLoans = new BookLoans();
				Book book = bookDao.getBookById(resultSet.getInt("bookId")) ;
				LibraryBranch libraryBranch = libraryBranchDao.getLibraryBranchById(resultSet.getInt("branchId"));
				Borrower borrower = borrowerDao.getBorrowerByCardNumber(resultSet.getInt("cardNo"));
				bookLoans.setBook(book);
				bookLoans.setLibraryBranch(libraryBranch);
				bookLoans.setBorrower(borrower);
				bookLoans.setDateOut(resultSet.getDate("dateOut"));
				bookLoans.setDueDate(resultSet.getDate("dueDate"));
				
				loanedBook.add(bookLoans);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loanedBook;
	}

}
