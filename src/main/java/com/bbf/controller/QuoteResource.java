package com.bbf.controller;

import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteRequest;
import com.bbf.model.json.ReadQuoteResponse;
import com.bbf.service.IQuoteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * JAX-RS resource class for handling URL shortening operations.
 */
@Path("/")
@Slf4j
public class QuoteResource {
    //private static final Logger LOG = Logger.getLogger(QuoteResource.class.toString());
    @Inject
    private IQuoteService currencyService;

    /**
     * Read quotes in db
     *
     * @param readQuoteRequest json object defining query contains
     * from UTC timestamp
     * to UTC timestamp
     * type optional: ASK, BID
     * @return list of quotes matches query parameters
     */
    @POST
    @Path("/read")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readQuotes(ReadQuoteRequest readQuoteRequest) {
        log.info("Read quote endpoint from:"+ readQuoteRequest.getFromTime()+", to:"+ readQuoteRequest.getToTime()+", type:"+ readQuoteRequest.getQuoteType());
        List<ReadQuoteResponse> quoteList = currencyService.readQuotes(readQuoteRequest.getFromTime(), readQuoteRequest.getToTime(), Optional.ofNullable(readQuoteRequest.getQuoteType()));
        log.info("Found : "+quoteList.size() +" quotes");
        return Response.ok(quoteList).build();
    }

    /**
     * Upload mangodb dump file in bson format
     *
     * @param parts http part containing file.
     * @return A response object containing either a redirection or a NOT FOUND status.
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBsonFile(List<EntityPart> parts) {
        log.info("Upload of MongoDB dump file");
        EntityPart file = parts.stream().filter(part -> "file".equals(part.getName())).findFirst().orElseThrow();
        int imported = currencyService.importToDb(file.getContent());
        return Response.ok("Imported entries: "+imported).build();
    }



}
