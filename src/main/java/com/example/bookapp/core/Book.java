package com.example.bookapp.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import  com.fasterxml.jackson.annotation.*;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "core.books.findAll",
                query = "select b from Book b")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "author_books",
            joinColumns = {  @JoinColumn(name="book_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name="author_id", referencedColumnName = "id") }
    )
    @JsonIgnoreProperties("book")
    private Set<Author> author = new HashSet<>();



    // Auto-generated equald, hashCode, getters and setters.


    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}