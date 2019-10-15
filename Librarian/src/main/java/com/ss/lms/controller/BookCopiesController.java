package com.ss.lms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.BookCopies;
import com.ss.lms.service.BookCopiesService;

@RestController
@RequestMapping("lms/")
public class BookCopiesController {

	@Autowired
	BookCopiesService bookCopiesService;
	
	@GetMapping("library-branch/id/{libraryBranchId}/book-list")
	public ResponseEntity<List<BookCopies>> getBookListByBranchId(@PathVariable int libraryBranchId){
		
		List<BookCopies> bookCopyList = bookCopiesService.getBookListByBranchId(libraryBranchId);
		if(!bookCopyList.isEmpty()) {
			return new ResponseEntity<>(bookCopyList,HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="library-branch/id/{libraryBranchId}/book/{bookId}",produces="text/bookCopies")
	public ResponseEntity<BookCopies> getBookCopies(@PathVariable int libraryBranchId,@PathVariable int bookId ){
		return bookCopiesService.getBookCopies(libraryBranchId, bookId);
	}
	
	
	@PutMapping("add-copies/branch-id/{branchId}/book-id/{bookId}/newNoOfCopies/{noOfCopies}")
	public ResponseEntity<String> updateNoOfCopies(@PathVariable int branchId, @PathVariable int bookId, @PathVariable int noOfCopies){
		return bookCopiesService.updateNoOfCopies(branchId, bookId, noOfCopies);
	}
}
