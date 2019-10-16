package com.ss.LMSBorrower.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.Entity.ReadBookLoanData;
import com.ss.LMSBorrower.service.BorrowerService;

@RestController

@RequestMapping("lms/")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})

public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	
	@PostMapping(path="book-checkout", consumes={"application/json","application/xml"})
	public ResponseEntity<String> checkOutBook(@RequestBody ReadBookLoanData readBookLoanData){
		
		return borrowerService.checkOutBook(readBookLoanData.getCardNumber(), readBookLoanData.getBranchId(), readBookLoanData.getBookId());
	}
	
	@GetMapping(path="see-loaned-books/card-number/{cardNumber}", produces={"application/json","application/xml"})
	public ResponseEntity<List<BookLoans>> getListOfLoanedBooks(@PathVariable int cardNumber){
		return borrowerService.getListOfLoanedBooks(cardNumber);
	}
	
	@DeleteMapping(path="book-return",consumes={"application/json","application/xml"})
	public ResponseEntity<String> returnBook(@RequestBody ReadBookLoanData readBookLoanData){
		return borrowerService.returnBook(readBookLoanData.getCardNumber(), readBookLoanData.getBranchId(), readBookLoanData.getBookId());
	}
}
