package com.bbf.controller;

import com.bbf.model.QuoteEntity;
import com.bbf.model.mongo.Quote;
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
     * @param from UTC timestamp
     * @param to UTC timestamp
     * @param type optional: ASK, BID
     * @return list of quotes matches query parameters
     */
    @GET
    @Path("/read")
    public Response readQuotes(@QueryParam("from") long from,@QueryParam("from") long to,@FormParam("type") String type) {
        log.info("read quote endpoint from:"+from+", to:"+to+", type:"+type);
        List<QuoteEntity> quoteList = currencyService.readQuotes(from,to, Optional.of(type));
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
