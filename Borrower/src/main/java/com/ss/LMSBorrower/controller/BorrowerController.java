package com.ss.LMSBorrower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.service.BorrowerService;

@RestController

@RequestMapping("lms/")
public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	
	@PutMapping("book-checkout/card-number/{cardNumber}/library-branch-id/{branchId}/book-id/{bookId}")
	public ResponseEntity<String> checkOutBook(@PathVariable int cardNumber, @PathVariable int branchId, @PathVariable int bookId ){
		return borrowerService.checkOutBook(cardNumber, branchId, bookId);
	}
	
	@GetMapping("see-loaned-books/card-number/{cardNumber}")
	public List<BookLoans> getListOfLoanedBooks(@PathVariable int cardNumber){
		return borrowerService.getListOfLoanedBooks(cardNumber);
	}
	
	@DeleteMapping("book-return/card-number/{cardNumber}/library-branch-id/{branchId}/book-id/{bookId}")
	public ResponseEntity<String> returnBook(@PathVariable int cardNumber, @PathVariable int branchId, @PathVariable int bookId ){
		return borrowerService.returnBook(cardNumber, branchId, bookId);
	}
}
