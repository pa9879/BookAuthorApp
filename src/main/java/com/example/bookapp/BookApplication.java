package com.example.bookapp;

import com.example.bookapp.core.Author;
import com.example.bookapp.core.Book;
import com.example.bookapp.db.AuthorDAO;
import com.example.bookapp.db.BookDAO;
//import com.hubspot.dropwizard.guice.*;
import com.example.bookapp.health.TemplateHealthCheck;
import com.example.bookapp.resource.AuthorResource;
import com.example.bookapp.resource.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DataSourceFactory;

public class BookApplication extends  Application<BookConfiguration>{
    public static void main(String[] args) throws Exception {
        new BookApplication().run(args);
    }

    private final HibernateBundle<BookConfiguration> hibernate = new HibernateBundle<BookConfiguration>(Book.class, Author.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BookConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<BookConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(BookConfiguration configuration,
                    Environment environment) throws  Exception{
//        final HelloWorldResource resource = new HelloWorldResource(
//                configuration.getTemplate(),
//                configuration.getDefaultName()
//        );
//        final TemplateHealthCheck healthCheck =
//                new TemplateHealthCheck(configuration.getTemplate());
        final BookDAO bookDAO
                = new BookDAO(hibernate.getSessionFactory());
        final AuthorDAO authorDAO
                = new AuthorDAO(hibernate.getSessionFactory());

//        environment.healthChecks().register("template", healthCheck);
//        environment.jersey().register(resource);

        environment.jersey().register(new BookResource(bookDAO));
        environment.jersey().register(new AuthorResource(authorDAO));
    }
}
