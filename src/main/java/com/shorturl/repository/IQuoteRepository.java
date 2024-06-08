package com.shorturl.repository;

import com.shorturl.model.Quote;
import com.shorturl.model.UrlEntity;

import java.util.List;

public interface IQuoteRepository {


    public Quote findById(long id);

    public Quote findByHashAndOriginalUrl(Quote urlEntity);

    void storeAll(List<Quote> quotes);
}
