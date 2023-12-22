package com.test.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.Target;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import com.test.bookstore.dao.BookDao;
import com.test.bookstore.model.Book;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {

	// @Autowired
	@Mock
	private BookDao bookDao;

	int id = 100;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveBookTest() {
		Book book = new Book(id, "TestBook", "Tester", "98983560923", new Date(), "test");
		bookDao.saveBook(book);
		assertThat(book.getId()).isGreaterThan(0);

	}

	@Test
	@Order(2)
	public void getEmployeeTest() {
		Book dummyBook = new Book(id, "TestBook", "Tester", "98983560923", new Date(), "test");
		when(bookDao.getBookById(id)).thenReturn(dummyBook);
		
		Book book = bookDao.getBookById(id);
		assertThat(book.getId()).isEqualTo(id);

	}

	@Test
	@Order(3)
	public void getListOfEmployeesTest() {

		List<Book> dummyList = new ArrayList<>();
		dummyList.add(new Book(id, "TestBook", "Tester", "98983560923", new Date(), "test"));
		when(bookDao.getAllBooks()).thenReturn(dummyList);

		List<Book> books = bookDao.getAllBooks();
		assertThat(books.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {

		Book dummyBook = new Book(id, "TestBook", "Tester", "98983560923", new Date(), "test");
		Book dummyBook2 = new Book(id, "TestBook", "Tester2", "98983560923", new Date(), "test");

		when(bookDao.getBookById(id)).thenReturn(dummyBook);
		Book book = bookDao.getBookById(id);

		when(bookDao.saveBook(book)).thenReturn(dummyBook2);
		book.setAuthor("Tester2");

		Book bookUpdated = bookDao.saveBook(book);
		assertThat(bookUpdated.getAuthor()).isEqualTo("Tester2");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {

		Book book = bookDao.getBookById(id);

		bookDao.deleteBook(book);

		Book deletedBook = bookDao.getBookById(id);

		assertThat(deletedBook).isNull();
	}
}
