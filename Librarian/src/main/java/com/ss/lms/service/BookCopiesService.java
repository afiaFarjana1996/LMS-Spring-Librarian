package com.ss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.lms.dao.BookCopiesDao;
import com.ss.lms.dao.BookDao;
import com.ss.lms.dao.LibraryBranchDao;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.LibraryBranch;

@Component
public class BookCopiesService {
    
	@Autowired
	BookCopiesDao bookCopiesDao;
	@Autowired 
	LibraryBranchDao libraryBranchDao;
	@Autowired
	BookDao bookDao;
	
	public List<BookCopies> getBookListByBranchId(int libraryBranchId){
		return bookCopiesDao.getBookListByLibraryBranchId(libraryBranchId);
	}
	
	
	public ResponseEntity<String> updateNoOfCopies(int branchId, int bookId, int noOfCopies){
		LibraryBranch libraryBranch = libraryBranchDao.getLibraryBranchById(branchId);
		Book book = bookDao.getBookById(bookId);
		
		if(libraryBranch.getBranchId()==0) {
			return new ResponseEntity<>("Library Branch doesn't exist.",HttpStatus.NOT_FOUND);
		}
		else {
			if(book.getBookId()==0) {
				return new ResponseEntity<>("Book doesn't exist.",HttpStatus.NOT_FOUND);
			}
			else {
				BookCopies bookCopies = new BookCopies(noOfCopies,book,libraryBranch);
				if(bookCopiesDao.checkIfCopiesExist(bookCopies)) {
					bookCopiesDao.updateCopies(bookCopies);
					return new ResponseEntity<>("update successfull.",HttpStatus.ACCEPTED);
				}
				else {
					bookCopiesDao.insertCopies(bookCopies);
					return new ResponseEntity<>("New book copy data created.",HttpStatus.CREATED);
				}
			}
		}
	}
}
