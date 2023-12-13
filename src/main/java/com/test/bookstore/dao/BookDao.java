package com.test.bookstore.dao;

import java.util.List;

import com.test.bookstore.model.Book;

public interface BookDao {

	public Book saveBook(Book book);
	
	public Book getBookById(Integer id);
	
	public List<Book> getAllBooks();
	
	public Book deleteBook(Book book);
}
