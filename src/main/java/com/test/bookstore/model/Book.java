package com.test.bookstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TITLE", nullable =  false)
	private String title;

	@Column(name = "AUTHOR",  nullable =  false)
	private String author;
	
	@Column(name = "ISBN",  nullable =  false)
	private String ISBN;

	@Column(name = "PUBLISHED_DATE",  nullable =  false)
	private Date publishedDate;

	@Column(name = "GENRE",  nullable =  false)
	private String genre;
	
	public Book() {
		super();
	}

	public Book(Integer id, String title, String author, String iSBN, Date publishedDate, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		ISBN = iSBN;
		this.publishedDate = publishedDate;
		this.genre = genre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}