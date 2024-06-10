package com.bbf.service;

import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteResponse;
import com.bbf.utils.ConversionUtils;
import com.bbf.utils.QuoteType;
import com.bbf.utils.ReadQuoteResponseFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ext.ContextResolver;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.EntityManager;
import org.apache.commons.collections4.ListUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Slf4j
public class QuoteService implements IQuoteService {

    @Inject
    private IQuoteRepository quoteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "batch_size")
    private int batchSize;

    @Inject
    private ContextResolver<ObjectMapper> mapperResolver;

    public List<ReadQuoteResponse> readQuotes(long from, long to, Optional<String> type) {
        List<QuoteEntity> entities = readQuoteEntities(from,to,type);
        return entities.parallelStream().map(e -> ReadQuoteResponseFactory.createQuoteResponse(e)).collect(Collectors.toList());
    }

    private List<QuoteEntity> readQuoteEntities(long from, long to, Optional<String> type) {
        long fromNano = ConversionUtils.convertToUnixTimestamp(from);
        long toNano = ConversionUtils.convertToUnixTimestamp(to);
        if (type.isPresent()){
            String typeAsString = type.get();
            if (QuoteType.ASK.toString().equals(typeAsString)){
                return quoteRepository.findByBoundariesWithAskPositive(fromNano, toNano);
            } else if (QuoteType.BID.toString().equals(typeAsString)){
                return quoteRepository.findByBoundariesWithBidPositive(fromNano, toNano);
            }

        }
        return quoteRepository.findByBoundaries(fromNano, toNano);
    }


    @Transactional
    @Override
    public int importToDb(InputStream content) {
        List<QuoteEntity> quotes = loadFromFile(content);
        log.info("Loaded from file "+quotes.size());
        List<List<QuoteEntity>> partition = ListUtils.partition(quotes, batchSize);
        for (List<QuoteEntity> quoteBatchList : partition) {
            log.info("Iteration store with "+quoteBatchList.size()+ " entries");
            quoteRepository.storeAll(quoteBatchList);
            entityManager.flush();
            log.info("Iteration store finished");
        }
        return quotes.size();
    }

    private List<QuoteEntity> loadFromFile(InputStream content){
        ObjectMapper mapper = mapperResolver.getContext(ObjectMapper.class);
        try (InputStream resource = new BufferedInputStream(content)) {
            InputStreamReader inputStreamReader = new InputStreamReader(resource, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> doc = bufferedReader.lines().collect(Collectors.toList());
            return doc.parallelStream().map(createMapper(mapper))
                     .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Function<String, QuoteEntity> createMapper(ObjectMapper mapper){
        return s -> {
            BasicDBObject bson = BasicDBObject.parse(s);
            String json = bson.toJson();
            try {
                Quote quote = mapper.readValue(json, Quote.class);
                return convertToEntity(quote);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private QuoteEntity convertToEntity(Quote quote) {
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setBid(quote.getBid());
        quoteEntity.setAsk(quote.getAsk());
        quoteEntity.setBidVolume(quote.getBidVolume());
        quoteEntity.setAskVolume(quote.getAskVolume());
        quoteEntity.setTime(quote.getTime());
        return quoteEntity;
    }
}
