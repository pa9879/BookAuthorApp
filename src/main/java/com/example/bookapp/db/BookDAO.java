package com.example.bookapp.db;

import com.example.bookapp.core.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import io.dropwizard.hibernate.*;
import com.google.common.base.Optional;
import java.util.List;

public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Book> findAll() {
        return list((Query<Book>)  namedQuery("core.books.findAll"));
    }

    public Optional<Book> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    public Book create(Book book) {
        return persist(book);
    }

}
