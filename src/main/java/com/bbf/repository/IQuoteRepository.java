package com.bbf.repository;

import com.bbf.model.QuoteEntity;
import com.bbf.model.mongo.Quote;

import java.util.List;

public interface IQuoteRepository {

    void storeAll(List<QuoteEntity> quotes);

    List<QuoteEntity> findByBoundaries(long from, long to);

    List<QuoteEntity> findByBoundariesWithAskPositive(long from, long to);

    List<QuoteEntity> findByBoundariesWithBidPositive(long from, long to);
}
