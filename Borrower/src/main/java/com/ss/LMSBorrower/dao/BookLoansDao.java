package com.ss.LMSBorrower.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.LMSBorrower.Entity.BookLoans;
import com.ss.LMSBorrower.Entity.BookLoansComposite;

@Repository
public interface BookLoansDao extends JpaRepository<BookLoans,BookLoansComposite>{

}
