package com.shorturl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.shorturl.model.Quote;
import com.shorturl.model.UrlEntity;
import com.shorturl.repository.IQuoteRepository;
import com.shorturl.utils.Base62EncoderDecoder;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@ApplicationScoped
public class QuoteService implements IQuoteService {

    private static final Logger LOG = Logger.getLogger(QuoteService.class.toString());

    @Inject
    private IQuoteRepository quoteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    @ConfigProperty(name = "domain")
    private String domain;




        public void performDatabaseOperation() {
            try (Connection connection = dataSource.getConnection()) {
                // Your database operation logic
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        long id = Base62EncoderDecoder.decodeLong(shortUrl);
        LOG.info(shortUrl+" translated "+id);
        Quote urlEntity = quoteRepository.findById(id);
        if (urlEntity!=null){
            return urlEntity.getOriginalUrl();
        }
        return null;
    }

    @Transactional
    @Override
    public String createShortUrl(String originalUrl){
        UrlEntity newUrlEntity = createUrlEntity(originalUrl);
        UrlEntity storedUrlEntity = storeOrRetrieve(newUrlEntity);
        String shortURL = Base62EncoderDecoder.encode(storedUrlEntity.getId());
        LOG.info("Entity with id "+storedUrlEntity.getId()+ " will be mapped to postfix  "+shortURL);
        return domain+"/"+shortURL;

    }
    @Transactional
    @Override
    public void importToDb(InputStream content) {
        List<Quote> quotes = loadFromFile(content);
        quoteRepository.storeAll(dataSource, quotes);

        entityManager.flush();
        entityManager.close();
    }

    private List<Quote> loadFromFile(InputStream content){
        List<Quote> ret = new ArrayList<>();
        try (InputStream resource = new BufferedInputStream(content)) {
            InputStreamReader inputStreamReader = new InputStreamReader(resource, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> doc = bufferedReader.lines().collect(Collectors.toList());

            for (String s : doc) {
                BasicDBObject bson = BasicDBObject.parse(s);
                String json = bson.toJson();
                Quote quote = objectMapper.readValue(json, Quote.class);
                ret.add(quote);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private UrlEntity storeOrRetrieve(UrlEntity entity) {
        UrlEntity alreadyStoredUrlEntity = quoteRepository.findByHashAndOriginalUrl(entity);
        LOG.info("Looking for entity with hash "+entity.getOriginalUrlHash());
        if (alreadyStoredUrlEntity!=null) {
            LOG.info("Found entity " + alreadyStoredUrlEntity.getId()+" "+alreadyStoredUrlEntity.getOriginalUrl());
            return alreadyStoredUrlEntity;
        }
        Long id = quoteRepository.store(entity);
        LOG.info("Not Found entity with id " + entity.getId()+" therefore persisting and getting id "+entity.getId());
        entity.setId(id);
        return entity;
    }

    private UrlEntity createUrlEntity(String originalUrl) {
        String hash = DigestUtils.md5Hex(originalUrl).toUpperCase();
        UrlEntity entity = new UrlEntity();
        entity.setOriginalUrl(originalUrl);
        entity.setOriginalUrlHash(hash);
        return entity;
    }

}
