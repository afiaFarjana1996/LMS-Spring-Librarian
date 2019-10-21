package com.ss.LMSBorrower.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoans implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6628056937248998616L;
	@EmbeddedId
	private BookLoansComposite bookLoansComposite;
	
	@Column(name = "dateOut")
	private Date dateOut;
	
	@Column(name = "dueDate")
	private Date dueDate;

	public BookLoans() {}

	public BookLoans(BookLoansComposite bookLoansComposite, Date dateOut, Date dueDate)
	{
		super();
		this.bookLoansComposite = bookLoansComposite;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookLoansComposite == null) ? 0 : bookLoansComposite.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoans other = (BookLoans) obj;
		if (bookLoansComposite == null)
		{
			if (other.bookLoansComposite != null)
				return false;
		} else if (!bookLoansComposite.equals(other.bookLoansComposite))
			return false;
		if (dateOut == null)
		{
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (dueDate == null)
		{
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "BookLoans [bookLoansComposite=" + bookLoansComposite + ", dateOut=" + dateOut + ", dueDate=" + dueDate + "]";
	}

	public BookLoansComposite getBookLoanKey()
	{
		return bookLoansComposite;
	}

	public void setBookLoanKey(BookLoansComposite bookLoansComposite)
	{
		this.bookLoansComposite = bookLoansComposite;
	}

	public Date getDateOut()
	{
		return dateOut;
	}

	public void setDateOut(Timestamp dateOut)
	{
		this.dateOut = dateOut;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate)
	{
		this.dueDate = dueDate;
	}

}
