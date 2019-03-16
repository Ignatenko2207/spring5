package it.discovery.repository;

import it.discovery.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//@Qualifier("xml")
public class XMLBookRepository implements BookRepository {

    private String filePath = "books.xml";

    @Override
    public void saveBook(Book book) {

    }

    @Override
    public Book findBookById(int id) {
        return null;
    }

    @Override
    public List<Book> findBooks() {
        return null;
    }
}
