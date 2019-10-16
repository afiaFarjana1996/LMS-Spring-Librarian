package com.ss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.ReadBookCopies;
import com.ss.lms.service.BookCopiesService;



@RestController
@RequestMapping("lms/")
public class BookCopiesController {

	@Autowired
	BookCopiesService bookCopiesService;
	
	@GetMapping(path="library-branch/id/{libraryBranchId}/book-list",produces = {"application/json","application/xml"})
	public ResponseEntity<List<BookCopies>> getBookListByBranchId(@PathVariable int libraryBranchId){
		
		return bookCopiesService.getBookListByBranchId(libraryBranchId);
		
	}
	
	@RequestMapping(path="library-branch/id/{libraryBranchId}/book/{bookId}",produces={"application/json","application/xml"})
	public ResponseEntity<BookCopies> getBookCopies(@PathVariable int libraryBranchId,@PathVariable int bookId ){
		return bookCopiesService.getBookCopies(libraryBranchId, bookId);
	}
	
	
	@PutMapping(path="copyAddition",produces = {"application/json","application/xml"}, consumes= {"application/json","application/xml"})
	public ResponseEntity<String> updateNoOfCopies(@RequestBody ReadBookCopies readBookCopies) {
		
		return bookCopiesService.updateNoOfCopies(readBookCopies.getBranchId(), readBookCopies.getBookId(), readBookCopies.getNoOfCopies());
	}
}
