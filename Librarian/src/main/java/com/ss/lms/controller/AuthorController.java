package com.ss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Author;
import com.ss.lms.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	@RequestMapping("lms/authors/{authorId}")
public Author getAuthorById(@PathVariable int authorId) {
	return authorService.getAuthorById(authorId);
}
}
