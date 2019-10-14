package com.ss.LMSBorrower.service;

import java.util.Calendar;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Book;
import com.ss.LMSBorrower.Entity.BookCopies;
import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.Entity.Borrower;
import com.ss.LMSBorrower.Entity.LibraryBranch;
import com.ss.LMSBorrower.dao.BookCopiesDao;
import com.ss.LMSBorrower.dao.BookDao;
import com.ss.LMSBorrower.dao.BookLoansDao;
import com.ss.LMSBorrower.dao.BorrowerDao;
import com.ss.LMSBorrower.dao.LibraryBranchDao;

@Component

public class BorrowerService {

	@Autowired
	BorrowerDao borrowerDao;
	@Autowired 
	LibraryBranchDao libraryBranchDao;
	@Autowired
	BookDao bookDao;
	@Autowired
	BookCopiesDao bookCopiesDao;
	@Autowired 
	BookLoansDao bookLoansDao;
	
	public ResponseEntity<String> checkOutBook(int cardNumber,int branchId, int bookId ){
		
		Borrower borrower = borrowerDao.getBorrowerByCardNumber(cardNumber);
		LibraryBranch libraryBranch= libraryBranchDao.getLibraryBranchById(branchId);
		Book book = bookDao.getBookById(bookId);
		
		BookCopies bookCopies = bookCopiesDao.getBookCopiesInformation(branchId, bookId);
		Boolean ifAlreadyCheckedOut = bookLoansDao.checkIfAlreadyCheckedOut(borrower, libraryBranch, book);
		
		if(borrower.getCardNo()==0) {
			return new ResponseEntity<String>("Borrower doesn't exist.",HttpStatus.NOT_FOUND);
		}else {
			if(libraryBranch.getBranchId()==0) {
				return new ResponseEntity<String>("Library Branch doesn't exist.",HttpStatus.NOT_FOUND);
			}
			else {
				if(book.getBookId()==0) {
					return new ResponseEntity<String>("Book doesn't exist.",HttpStatus.NOT_FOUND);
				}
				else {
					if(bookCopies.getNoOfCopies()==0) {
						return new ResponseEntity<String>("The provided book doesn't exist in the branch.\n"
								+ "Please try to checkout the book from another branch\n"
								+ "Or see resources to check the books existing in this branch.",HttpStatus.NOT_FOUND);
					}
					else {
						if(ifAlreadyCheckedOut) {
							return new ResponseEntity<String>("You have already checked out this book.\n"
									+ "Try checking it out of another branch",HttpStatus.CONFLICT);
						}else {
							
						bookCopies.setNoOfCopies((bookCopies.getNoOfCopies()-1));
						BookLoans bookLoans = new BookLoans();
						bookLoans.setBook(book);
						bookLoans.setBorrower(borrower);
						bookLoans.setLibraryBranch(libraryBranch);
						
						Calendar c = Calendar.getInstance();
					    Date dateOut = new Date();  
					    c.add(Calendar.DATE, 7);
					    Date dueDate = c.getTime();
					    bookLoans.setDateOut(dateOut);
					    bookLoans.setDueDate(dueDate);
					    
					    bookLoansDao.insertIntoBookLoans(bookLoans);
						bookCopiesDao.updateNoOfBookCopies(bookCopies);
						return new ResponseEntity<String>("Checkout successful",HttpStatus.ACCEPTED);
						}
					}
				}
			}
		}
	}
	
	public List<BookLoans> getListOfLoanedBooks(int cardNumber){
		return bookLoansDao.getListOfLoanedBooks(cardNumber);
	}
	
	public ResponseEntity<String> returnBook(int cardNumber, int branchId, int bookId ){
		Borrower borrower = borrowerDao.getBorrowerByCardNumber(cardNumber);
		LibraryBranch libraryBranch= libraryBranchDao.getLibraryBranchById(branchId);
		Book book = bookDao.getBookById(bookId);
		
		BookCopies bookCopies = bookCopiesDao.getBookCopiesInformation(branchId, bookId);
		Boolean ifAlreadyCheckedOut = bookLoansDao.checkIfAlreadyCheckedOut(borrower, libraryBranch, book);
		
		if(borrower.getCardNo()==0) {
			return new ResponseEntity<String>("Borrower doesn't exist.",HttpStatus.NOT_FOUND);
		}else {
			if(libraryBranch.getBranchId()==0) {
				return new ResponseEntity<String>("Library Branch doesn't exist.",HttpStatus.NOT_FOUND);
			}
			else {
				if(book.getBookId()==0) {
					return new ResponseEntity<String>("Book doesn't exist.",HttpStatus.NOT_FOUND);
				}
				else {
					if(bookCopies.getNoOfCopies()==0) {
						return new ResponseEntity<String>("The provided book doesn't exist in the branch.\n"
								+ "Please try to checkout the book from another branch\n"
								+ "Or see resources to check the books existing in this branch.",HttpStatus.NOT_FOUND);
					}
					else {
						if(!ifAlreadyCheckedOut) {
							return new ResponseEntity<String>("You have not checked out this book from this branch.",HttpStatus.NOT_ACCEPTABLE);
						}else {
							bookCopies.setNoOfCopies((bookCopies.getNoOfCopies()+1));
							bookCopiesDao.updateNoOfBookCopies(bookCopies);
							BookLoans bookLoans = new BookLoans();
							bookLoans.setBook(book);
							bookLoans.setLibraryBranch(libraryBranch);
							bookLoans.setBorrower(borrower);
							bookLoansDao.returnBook(bookLoans);
						return new ResponseEntity<String>("return successful",HttpStatus.ACCEPTED);
						}
					}
				}
			}
		}
	}
}
