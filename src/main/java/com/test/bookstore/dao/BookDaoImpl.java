package com.test.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.test.bookstore.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Book saveBook(Book book) {
		try {
			entityManager.unwrap(Session.class).save(book);
			System.out.print("Saved success");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	@Transactional
	public Book getBookById(Integer id) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Book.class);
		criteria.add(Restrictions.eq("id", id));
		Book book = (Book) criteria.uniqueResult();
		return book;
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Book.class);
		List<Book> list = criteria.list();
		return list;
	}

	@Override
	@Transactional
	public Book deleteBook(Book book) {
		entityManager.unwrap(Session.class).delete(book);
		return book;
	}

}
