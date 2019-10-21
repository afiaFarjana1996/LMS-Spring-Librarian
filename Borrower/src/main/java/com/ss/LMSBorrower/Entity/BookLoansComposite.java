package com.ss.LMSBorrower.Entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BookLoansComposite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2301326447475011236L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId", nullable = false)
	private Book book;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", nullable = false)
	private LibraryBranch branch;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cardNo", nullable = false)
	private Borrower borrower;
	
	public BookLoansComposite() {
		super();
	}

	public BookLoansComposite(Book book, LibraryBranch branch, Borrower borrower) {
		super();
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
	}
	
	public Book getBook() {
		return book;
	}
	

	public void setBook(Book book) {
		this.book = book;
	}

	

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BookLoansComposite other = (BookLoansComposite) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "BookLoansComposite [book=" + book + ", branch=" + branch + ", borrower=" + borrower +"]";
	}
}
