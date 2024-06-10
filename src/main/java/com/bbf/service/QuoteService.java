package com.bbf.service;

import com.bbf.model.QuoteEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.bbf.model.mongo.Quote;
import com.bbf.repository.IQuoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ext.ContextResolver;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.EntityManager;

@ApplicationScoped
@Slf4j
public class QuoteService implements IQuoteService {

    private static final Logger LOG = Logger.getLogger(QuoteService.class.toString());

    @Inject
    private IQuoteRepository quoteRepository;

    @PersistenceContext
    private EntityManager entityManager;

   // @Inject
   // private ObjectMapper objectMapper;

    @Inject private ContextResolver<ObjectMapper> mapperResolver;

    public List<QuoteEntity> readQuotes(long from, long to, Optional<String> type) {
        if (type.isPresent()){
            String typeAsString = type.get();
            if ("ASK".equals(typeAsString)){
                return quoteRepository.findByBoundariesWithAskPositive(from,to);
            }else if ("BID".equals(typeAsString)){
                return quoteRepository.findByBoundariesWithBidPositive(from,to);
            }

        }
        return quoteRepository.findByBoundaries(from,to);
    }


    @Transactional
    @Override
    public int importToDb(InputStream content) {
        List<Quote> quotes = loadFromFile(content);
        List<QuoteEntity> quoteEntities = convertToEntities(quotes);
        quoteRepository.storeAll(quoteEntities);

        entityManager.flush();
        return quotes.size();
    }

    private List<QuoteEntity> convertToEntities(List<Quote> quotes) {
        List<QuoteEntity> ret = new ArrayList<>();
        for (Quote quote : quotes) {
            QuoteEntity quoteEntity = new QuoteEntity();
            quoteEntity.setBid(quote.getBid());
            quoteEntity.setAsk(quote.getAsk());
            quoteEntity.setBidVolume(quote.getBidVolume());
            quoteEntity.setAskVolume(quote.getAskVolume());
            quoteEntity.setTime(quote.getTime());
            ret.add(quoteEntity);
        }
        return ret;
    }

    private List<Quote> loadFromFile(InputStream content){
        List<Quote> ret = new ArrayList<>();
        try (InputStream resource = new BufferedInputStream(content)) {
            InputStreamReader inputStreamReader = new InputStreamReader(resource, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> doc = bufferedReader.lines().collect(Collectors.toList());

            for (String s : doc) {
                log.info("parsing DBObject:"+s);
                BasicDBObject bson = BasicDBObject.parse(s);
                String json = bson.toJson();
                log.info("parsing json:"+json);
                Quote quote = mapperResolver.getContext(ObjectMapper.class).readValue(json, Quote.class);
                ret.add(quote);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }


}
