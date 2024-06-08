package com.shorturl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shorturl.model.LongUrlWrapper;
import com.shorturl.service.IQuoteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Logger;

/**
 * JAX-RS resource class for handling URL shortening operations.
 */
@Path("/")
public class QuoteResource {
    private static final Logger LOG = Logger.getLogger(QuoteResource.class.toString());
    @Inject
    private IQuoteService currencyService;



    /**
     * Convert a new URL to short url
     *
     * @param longUrlWrapper the url which needs to be shorten
     * @return short url representing originalUrl
     */
    @POST
    @Path("/shortify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String shortify(LongUrlWrapper longUrlWrapper) {
        LOG.info("shortify "+longUrlWrapper.getLongUrl());
        System.out.println(longUrlWrapper.getLongUrl());
        String shortURL = currencyService.createShortUrl(longUrlWrapper.getLongUrl());
        new ObjectMapper()
        return shortURL;
    }

    /**
     * Mango db dump file in bson format
     *
     * @param shortUrl The short URL to be resolved to its original URL.
     * @return A response object containing either a redirection or a NOT FOUND status.
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadBsonFile(List<EntityPart> parts) {
        EntityPart file = parts.stream().filter(part -> "file".equals(part.getName())).findFirst().orElseThrow();
        currencyService.importToDb(file.getContent());
        return "OK";
    }



}
