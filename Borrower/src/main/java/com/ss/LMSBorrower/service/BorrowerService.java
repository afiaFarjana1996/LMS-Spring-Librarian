package com.ss.LMSBorrower.service;

import java.util.Calendar;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.LMSBorrower.Entity.Book;
import com.ss.LMSBorrower.Entity.BookCopies;
import com.ss.LMSBorrower.Entity.BookCopiesComposite;
import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.Entity.BookLoansComposite;
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
	
	public ResponseEntity<?> checkOutBook(int cardNumber,int branchId, int bookId ){
		
		Optional<Borrower> borrower = borrowerDao.findById(cardNumber);
		Optional<LibraryBranch> libraryBranch= libraryBranchDao.findById(branchId);
		Optional<Book> book = bookDao.findById(bookId);
		
		if(!borrower.isPresent() || !book.isPresent() || !borrower.isPresent()) {
			return new ResponseEntity<String>("No such entity",HttpStatus.UNPROCESSABLE_ENTITY);
		}else {
		BookLoansComposite bookLoansComposite = new BookLoansComposite(book.get(),libraryBranch.get(),borrower.get());
		Optional<BookLoans> getBookLoan = bookLoansDao.findById(bookLoansComposite);
		if(getBookLoan.isPresent()) {
			return new ResponseEntity<String>("You have already loaned this book",HttpStatus.BAD_REQUEST);
		}else {
			BookCopiesComposite bookCopiesComposite = new BookCopiesComposite(book.get(),libraryBranch.get());
			Optional<BookCopies> OpBookCopies = bookCopiesDao.findById(bookCopiesComposite);
			BookCopies oldBookCopies = OpBookCopies.get();
			if(oldBookCopies.getNoOfCopies()==0) {
				return new ResponseEntity<String>("This book doesn't exist in this branch",HttpStatus.CONFLICT);
			}else {
			BookCopies newBookCopies = new BookCopies(bookCopiesComposite,(OpBookCopies.get().getNoOfCopies()-1));
			bookCopiesDao.save(newBookCopies);
							
						Calendar c = Calendar.getInstance();
					    Date dateOut = new Date();  
					    c.add(Calendar.DATE, 7);
					    Date dueDate = c.getTime();
					    BookLoans bookLoans = new BookLoans(bookLoansComposite,dateOut,dueDate);
					    
					    bookLoansDao.save(bookLoans);
						return new ResponseEntity<String>("Checkout successful",HttpStatus.ACCEPTED);
						
	}
		}
		}
	}
		
	
	
	public ResponseEntity<?> getListOfLoanedBooks(int cardNumber){
		Optional<Borrower> borrower = borrowerDao.findById(cardNumber);
		if(!borrower.isPresent()) {
			return new ResponseEntity<String>("Borrower doesn't exist",HttpStatus.NOT_FOUND);
		}else {
			List<BookLoans> bookLoansList = bookLoansDao.findAll().stream()
					.filter(x-> x.getBookLoanKey().getBorrower().equals(borrower))
					.collect(Collectors.toList());
			return new ResponseEntity<List<BookLoans>>(bookLoansList,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> returnBook(int cardNumber, int branchId, int bookId ){
		Optional<Borrower> borrower = borrowerDao.findById(cardNumber);
		Optional<LibraryBranch> libraryBranch= libraryBranchDao.findById(branchId);
		Optional<Book> book = bookDao.findById(bookId);
		if(!borrower.isPresent() || !book.isPresent() || !borrower.isPresent()) {
			return new ResponseEntity<String>("No such entity",HttpStatus.UNPROCESSABLE_ENTITY);
		}else {
			BookLoansComposite bookLoansComposite = new BookLoansComposite(book.get(),libraryBranch.get(),borrower.get());
			Optional<BookLoans> getBookLoan = bookLoansDao.findById(bookLoansComposite);
			if(!getBookLoan.isPresent()) {
				return new ResponseEntity<String>("You have already returned this book",HttpStatus.BAD_REQUEST);
			}else {
				BookCopiesComposite bookCopiesComposite = new BookCopiesComposite(book.get(),libraryBranch.get());
				Optional<BookCopies> OpBookCopies = bookCopiesDao.findById(bookCopiesComposite);
				BookCopies newBookCopies = new BookCopies(bookCopiesComposite,(OpBookCopies.get().getNoOfCopies()-1));
				bookCopiesDao.save(newBookCopies);
				BookLoans bookLoans = bookLoansDao.findById(bookLoansComposite).get();
				bookLoansDao.delete(bookLoans);
				return new ResponseEntity<String>("Return successfull",HttpStatus.OK);
			}
		}
		
		
	}
}
