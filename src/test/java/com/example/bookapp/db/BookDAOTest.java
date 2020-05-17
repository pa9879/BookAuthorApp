package com.example.bookapp.db;

import com.example.bookapp.core.Book;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BookDAOTest {

    public DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(Book.class)
            .build();

    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() throws Exception {
       bookDAO = new BookDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createBook() {
        final Book jeff = daoTestRule.inTransaction(() -> bookDAO.create(new Book("Jeff")));
        assertThat(jeff.getId()).isGreaterThan(0);
        assertThat(jeff.getBookName()).isEqualTo("Jeff");
        assertThat(bookDAO.findById(jeff.getId())).isEqualTo(Optional.of(jeff));
    }
}
