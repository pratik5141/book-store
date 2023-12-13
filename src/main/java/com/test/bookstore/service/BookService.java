package com.test.bookstore.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface BookService {

	public ObjectNode addNewBook(JsonNode input);

	public ObjectNode getBookList();

	public ObjectNode updateBookDetails(Integer id, JsonNode input);

	public ObjectNode deleteBook(Integer id);

	public ObjectNode getBookById(Integer id);

}
