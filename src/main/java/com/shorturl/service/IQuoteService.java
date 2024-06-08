package com.shorturl.service;

import java.io.InputStream;

public interface IQuoteService {
    public String getOriginalUrl(String shortUrl);

    public String createShortUrl(String originalUrl);

    void importToDb(InputStream content);
}
