package it.discovery.service;

import it.discovery.model.Book;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookService {
	void saveBook(Book book);

	Book findBookById(int id);

	CompletableFuture<List<Book>> findBooks();
}
