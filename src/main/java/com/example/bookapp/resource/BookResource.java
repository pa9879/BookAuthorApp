package com.example.bookapp.resource;

import com.example.bookapp.core.Book;
import com.example.bookapp.db.BookDAO;
import com.example.bookapp.db.BookDAO;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.validation.Valid;
import io.dropwizard.jersey.params.LongParam;
import javax.ws.rs.core.MediaType;
import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private BookDAO bookDAO;

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @POST
    @UnitOfWork
    public Book addBook(@Valid Book book) {
        return bookDAO.create(book);
    }

    @GET
    @UnitOfWork
    public List<Book> findBooks() {
        return bookDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Book> findById(@PathParam("id") LongParam id) {
        return bookDAO.findById(id.get());
    }

}
