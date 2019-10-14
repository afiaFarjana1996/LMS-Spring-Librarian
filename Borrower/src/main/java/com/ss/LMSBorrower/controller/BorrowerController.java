package com.ss.LMSBorrower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.service.BorrowerService;

@RestController

public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	
	@RequestMapping(path="lms/borrower/card-number/{cardNumber}/library-branch-id/{branchId}/book-id/{bookId}",method=RequestMethod.PUT)
	public ResponseEntity<String> checkOutBook(@PathVariable int cardNumber, @PathVariable int branchId, @PathVariable int bookId ){
		return borrowerService.checkOutBook(cardNumber, branchId, bookId);
	}
	
	@RequestMapping("lms/see-loaned-books/card-number/{cardNumber}")
	public List<BookLoans> getListOfLoanedBooks(@PathVariable int cardNumber){
		return borrowerService.getListOfLoanedBooks(cardNumber);
	}
}
