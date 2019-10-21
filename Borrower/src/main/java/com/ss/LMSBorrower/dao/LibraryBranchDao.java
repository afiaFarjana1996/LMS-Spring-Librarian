package com.ss.LMSBorrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ss.LMSBorrower.Entity.LibraryBranch;


@Repository
public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Integer>{


}
