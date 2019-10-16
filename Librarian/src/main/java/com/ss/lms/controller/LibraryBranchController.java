package com.ss.lms.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.service.LibraryBranchService;

@RestController
@RequestMapping("lms/library-branch/")
public class LibraryBranchController {
    
	@Autowired
	LibraryBranchService libraryBranchService;
	
	@GetMapping(path="list",produces = {"application/json","application/xml"})
	public List<LibraryBranch> getAllLibraryBranch() {
		return libraryBranchService.getAllLibraryBranch();
	}
	
	@GetMapping(path="id/{libraryBranchId}",produces = {"application/json","application/xml"})
	public ResponseEntity<LibraryBranch> getLibraryBranchById(@PathVariable int libraryBranchId){
		LibraryBranch libraryBranch = libraryBranchService.getLibraryBranchById(libraryBranchId);
		
		if(libraryBranch.getBranchId()!=0) {
			return new ResponseEntity<LibraryBranch>(libraryBranch,HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path="branchChange",consumes={"application/json","application/xml"})
	public ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		
		return libraryBranchService.updateLibraryBranch(libraryBranch);
		
	}
	
	
}
