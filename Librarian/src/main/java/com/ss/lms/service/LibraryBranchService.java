package com.ss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.lms.dao.LibraryBranchDao;
import com.ss.lms.entity.LibraryBranch;

@Component
public class LibraryBranchService {
	
	private static LibraryBranchService instance = null;

	private LibraryBranchService() {
		// Exists only to defeat instantiation.
	}

	public static LibraryBranchService getInstance() {
		if (instance == null) {
			instance = new LibraryBranchService();
		}
		return instance;
	}

	@Autowired
	LibraryBranchDao libraryBranchDao;
	
	public List<LibraryBranch> getAllLibraryBranch() {
		return libraryBranchDao.getAllLibraryBranch();
	}
	
	public LibraryBranch getLibraryBranchById(Integer libraryBranchId) {
		return libraryBranchDao.getLibraryBranchById(libraryBranchId);
	}
	
	public ResponseEntity<String> updateLibraryBranch(LibraryBranch libraryBranch) {
		if(libraryBranchDao.doesLibraryBranchExist(libraryBranch)) {
			libraryBranchDao.updateLibraryBranch(libraryBranch);
			return new ResponseEntity<>("Library Branch Successfully updated.",HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>("Library Branch doesn't exist.",HttpStatus.NOT_FOUND);
		}
		
	}
}
