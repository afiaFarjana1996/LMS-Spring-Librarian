package com.ss.lms.entity;

public class BookCopies {
	public BookCopies(int noOfCopies, Book book, LibraryBranch libraryBranch) {
		super();
		this.noOfCopies = noOfCopies;
		this.book = book;
		this.libraryBranch = libraryBranch;
	}

	private int noOfCopies;
	private Book book;
	private LibraryBranch libraryBranch;

	/**
	 * @return the noOfCopies
	 */
	public int getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the libraryBranch
	 */
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	/**
	 * @param libraryBranch the libraryBranch to set
	 */
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

}
