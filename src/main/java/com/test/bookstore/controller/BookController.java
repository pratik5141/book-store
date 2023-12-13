package com.test.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.bookstore.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@GetMapping(value ="/welcome")
	public String welcome() {
		return "Welcome";
	}
	
	@PostMapping(value ="/add-new-book")
	public ObjectNode addNeeBook(@RequestBody String jsonInputString) {
		ObjectNode result = mapper.createObjectNode();
		try {
			JsonNode input = mapper.readTree(jsonInputString);
			result = bookService.addNewBook(input);
		} catch (Exception e) {
			result.put("STATUS", "999");
			result.put("message", "Internal Server Error!");
		}	
		return result;
	}
	
	@GetMapping(value = "/get-book-list")
	public ObjectNode getBookList() {
		ObjectNode result = mapper.createObjectNode();
		try {
			result = bookService.getBookList();
		} catch (Exception e) {
			result.put("STATUS", "999");
			result.put("message", "User not found!");
		}	
		return result;
	}
	
	@GetMapping(value = "/get-book/{id}")
	public ObjectNode getBookListById(@PathVariable Integer id) {
		ObjectNode result = mapper.createObjectNode();
		try {
			result = bookService.getBookById(id);
		} catch (Exception e) {
			result.put("STATUS", "999");
			result.put("message", "User not found!");
		}	
		return result;
	}
	
	
	@PutMapping(value = "/update-book/{id}")
	public ObjectNode updateBookDetails(@PathVariable Integer id, @RequestBody String jsonInputString) {
		ObjectNode result = mapper.createObjectNode();
		try {
			JsonNode input = mapper.readTree(jsonInputString);
			result = bookService.updateBookDetails(id, input);
		} catch (Exception e) {
			result.put("STATUS", "999");
			result.put("message", "Internal Server Error!");
		}	
		return result;
	}
	
	@DeleteMapping(value = "/delete-book/{id}")
	public ObjectNode deleteBookDetails(@PathVariable Integer id) {
		ObjectNode result = mapper.createObjectNode();
		try {
			result = bookService.deleteBook(id);
		} catch (Exception e) {
			result.put("STATUS", "999");
			result.put("message", "User not found!");
		}	
		return result;
	}

}
