package com.bbf.service;

import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteResponse;
import com.bbf.model.mongo.Quote;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface IQuoteService {
    List<ReadQuoteResponse> readQuotes(long from, long to, Optional<String> type);

    int importToDb(InputStream content);
}
