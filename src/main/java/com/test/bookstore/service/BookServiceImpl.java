package com.test.bookstore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.bookstore.dao.BookDao;
import com.test.bookstore.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public ObjectNode addNewBook(JsonNode input) {

		ObjectNode result = mapper.createObjectNode();
		Book book = new Book();
		book.setId(input.get("id").asInt());
		book.setTitle(input.get("title").asText());
		book.setAuthor(input.get("author").asText());
		book.setISBN(input.get("isbn").asText());

		// parsing date from string to date format
		Date publishedDate = parseStringToDate(input.get("publishedDate").asText());
		book.setPublishedDate(publishedDate);

		book.setISBN(input.get("isbn").asText());
		book.setGenre(input.get("genre").asText());

		Book savedBook = bookDao.saveBook(book);
		result.put("STATUS", "200");
		result.put("MESSAGE", "Book added successfully");
		result.putPOJO("DATA", savedBook);

		return result;
	}

	@Override
	public ObjectNode getBookList() {
		ObjectNode result = mapper.createObjectNode();
		List<Book> bookList = bookDao.getAllBooks();
		result.put("STATUS", "200");
		result.put("MESSAGE", "All books fetched Successfully");
		result.putPOJO("DATA", bookList);
		return result;
	}

	@Override
	public ObjectNode updateBookDetails(Integer id, JsonNode input) {
		ObjectNode result = mapper.createObjectNode();
		Book book = bookDao.getBookById(id);

		if (ObjectUtils.isEmpty(book)) {
			result.put("STATUS", "200");
			result.put("MESSAGE", "No book found for id:" + id);
			return result;
		}

		if (input.has("title"))
			book.setTitle(input.get("title").asText());

		if (input.has("author"))
			book.setAuthor(input.get("author").asText());

		// parsing date from string to date format
		if (input.has("publishedDate")) {
			Date publishedDate = parseStringToDate(input.get("publishedDate").asText());
			book.setPublishedDate(publishedDate);
		}

		if (input.has("isbn"))
			book.setISBN(input.get("isbn").asText());

		if (input.has("genre"))
			book.setGenre(input.get("genre").asText());

		bookDao.saveBook(book);

		result.put("STATUS", "200");
		result.put("MESSAGE", "Book updated successfully for id:" + id);
		return result;
	}

	@Override
	public ObjectNode deleteBook(Integer id) {
		ObjectNode result = mapper.createObjectNode();
		Book book = bookDao.getBookById(id);
		
		if (ObjectUtils.isEmpty(book)) {
			result.put("STATUS", "200");
			result.put("MESSAGE", "No book found for id:" + id);
			return result;
		}

		bookDao.deleteBook(book);

		result.put("STATUS", "200");
		result.put("MESSAGE", "Book deleted successfully for id:" + id);
		return result;
	}

	@Override
	public ObjectNode getBookById(Integer id) {
		ObjectNode result = mapper.createObjectNode();
		Book book = bookDao.getBookById(id);

		if (ObjectUtils.isEmpty(book)) {
			result.put("STATUS", "200");
			result.put("MESSAGE", "No book found for id:" + id);
			return result;
		}

		result.put("STATUS", "200");
		result.put("MESSAGE", "Book fetched Successfully");
		result.putPOJO("DATA", book);
		return result;
	}

	public Date parseStringToDate(String inputDate) {
		Date parsedDate = new Date();
		try {
			parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

}
