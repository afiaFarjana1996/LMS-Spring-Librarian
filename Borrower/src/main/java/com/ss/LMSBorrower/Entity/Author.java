package com.ss.LMSBorrower.Entity;

import java.io.Serializable;


import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_author")
public class Author implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8163572615058121473L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
	
	@Column(name = "authorName")
    private String authorName;
    
	public Integer getId() {
		return authorId;
	}
	public void setId(Integer id) {
		this.authorId = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
