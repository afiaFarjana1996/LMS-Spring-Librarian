package com.ss.LMSBorrower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.LMSBorrower.service.BorrowerService;

@RestController

public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	
	@RequestMapping("lms/borrower/card-number/{cardNumber}/library-branch-id/{branchId}/book-id/{bookId}")
	public ResponseEntity<String> checkOutBook(@PathVariable int cardNumber, @PathVariable int branchId, @PathVariable int bookId ){
		return borrowerService.checkOutBook(cardNumber, branchId, bookId);
	}
}
