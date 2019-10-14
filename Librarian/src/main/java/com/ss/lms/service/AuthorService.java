package com.ss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.dao.AuthorDao;
import com.ss.lms.entity.Author;

@Component
public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	public Author getAuthorById(int id) {
		
		return authorDao.getAuthorById(id);
	}
}
