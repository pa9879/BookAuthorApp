package com.example.bookapp.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import  com.fasterxml.jackson.annotation.*;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "core.author.findAll",
                query = "select a from Author a"),
        @NamedQuery(name = "core.author.findBooks",
                query = "select a.book from Author a "
                        + "where a.id = :id ")
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(mappedBy="author", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("author")
    private Set<Book> book = new HashSet<>();

    // Auto-generated equald, hashCode, getters and setters.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(authorName, author.authorName) &&
                Objects.equals(book, author.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, book);
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}