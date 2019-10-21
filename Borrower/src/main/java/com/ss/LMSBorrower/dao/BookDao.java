package com.ss.LMSBorrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.LMSBorrower.Entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book,Integer>{

}
