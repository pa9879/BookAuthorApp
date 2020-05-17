package com.example.bookapp;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.Provides;
import io.dropwizard.db.DataSourceFactory;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.hibernate.validator.group.GroupSequenceProvider;

public class BookAuthorModule implements Module {

    @Override
    public void configure(Binder binder) {
//        binder.bind(SampleResource.class).in(Singleton.class);
    }

    @Provides
    DataSourceFactory provideDatabase() {
        return new DataSourceFactory();
    }
}
