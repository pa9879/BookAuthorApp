package com.example.bookapp.resource;

import com.example.bookapp.core.Author;
import com.example.bookapp.core.Book;
import com.example.bookapp.db.AuthorDAO;
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

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private AuthorDAO authorDAO;

    public AuthorResource(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }
    @POST
    @UnitOfWork
    public Author addAuthor(@Valid Author author) {
        return authorDAO.create(author);
    }

    @GET
    @UnitOfWork
    public List<Author> findAuthors() {
        return authorDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Author> findById(@PathParam("id") LongParam id) {
        return authorDAO.findById(id.get());
    }

    @GET
    @Path("/{id}/books")
    @UnitOfWork
    public List<Author> findBooks(@PathParam("id") LongParam id) {
        return authorDAO.findBooks(id.get());
    }
}
