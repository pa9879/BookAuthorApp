package com.example.bookapp.db;

import com.example.bookapp.core.Author;
import com.example.bookapp.core.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import io.dropwizard.hibernate.*;
import com.google.common.base.Optional;
import java.util.List;

public class AuthorDAO extends AbstractDAO<Author> {

    public AuthorDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Author> findAll() {
        return list((Query<Author>)  namedQuery("core.author.findAll"));
    }

    public Optional<Author> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    public List<Author> findBooks(long id) {
        return list((Query<Author>)  namedQuery("core.author.findBooks")
                                         .setParameter("id", id));
    }

    public Author create(Author author) {
        return persist(author);
    }

}
