package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository repository;

	private List<BookRepository> repositories;

	@Autowired
	public BookServiceImpl(@NonNull BookRepository repository) {
		this.repository = repository;
		System.out.println("Using db repository");
	}

	@Autowired
	public void setRepositories(List<BookRepository> repositories) {
		this.repositories = repositories;
	}

	@Override
	public void saveBook(Book book) {
		repository.saveBook(book);
	}
	
	@Override
	public Book findBookById(int id) {
		return repository.findBookById(id);
	}

	@Override
	public List<Book> findBooks() {
		return repository.findBooks();
	}

	public List<BookRepository> getRepositories() {
		return repositories;
	}
}
