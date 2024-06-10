package com.bbf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import com.bbf.model.json.ReadQuoteRequest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Run integration tests against the server and the deployed application.
 */
@RunAsClient
@ExtendWith(ArquillianExtension.class)
public class ExchangeApplicationIT {

    @Test
    public void testReadEndpoint() {
        ReadQuoteRequest readQuoteRequest = new ReadQuoteRequest();
        readQuoteRequest.setFromTime(1533723600198l);
        readQuoteRequest.setToTime(1533723600199l);
        readQuoteRequest.setQuoteType(null);
        try (Client client = ClientBuilder.newClient()) {
            Response response = client
                    .target(URI.create("http://localhost:8080"))
                    .path("/api/read")
                    .request().post(Entity.json(readQuoteRequest));
            assertEquals(200, response.getStatus());

        }
    }
}
