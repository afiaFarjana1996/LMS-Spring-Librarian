package com.ss.LMSBorrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ss.LMSBorrower.Entity.BookCopies;
import com.ss.LMSBorrower.Entity.BookCopiesComposite;


@Repository
public interface BookCopiesDao extends JpaRepository<BookCopies, BookCopiesComposite>{

}
