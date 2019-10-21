package com.ss.LMSBorrower.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.LMSBorrower.Entity.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower,Integer>{

}
