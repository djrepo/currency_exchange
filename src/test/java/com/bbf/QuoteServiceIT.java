package com.bbf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bbf.config.JacksonConfig;
import com.bbf.config.JaxRsActivator;
import com.bbf.controller.QuoteResource;
import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteRequest;
import com.bbf.model.json.ReadQuoteResponse;
import com.bbf.model.mongo.Quote;
import com.bbf.repository.IQuoteRepository;
import com.bbf.repository.QuoteRepository;
import com.bbf.service.IQuoteService;
import com.bbf.service.QuoteService;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * Run integration tests with Arquillian to be able to test CDI beans
 */
@ExtendWith(ArquillianExtension.class)
@Slf4j
//@CleanupUsingScript("clean-database.sql")
//@UsingDataSet("init-database.xml")
public class QuoteServiceIT {

    @Deployment(testable = false)
    public static WebArchive createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(QuoteEntity.class.getPackage())
                .addPackage(Quote.class.getPackage())
                .addPackage(JacksonConfig.class.getPackage())
                .addPackage(ReadQuoteRequest.class.getPackage())
                .addClasses( QuoteResource.class, IQuoteService.class, QuoteService.class, IQuoteRepository.class, QuoteRepository.class,JaxRsActivator.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                //.addAsWebInfResource("beans.xml", "META-INF/beans.xml")
                .addAsWebInfResource("arquillian-ds.xml")
                .addAsResource("META-INF/microprofile-config.properties")
                //.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
                .addAsWebInfResource(new StringAsset("<beans xmlns=\"https://jakarta.ee/xml/ns/jakartaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "xsi:schemaLocation=\"https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/beans_3_0.xsd\"\n"
                + "bean-discovery-mode=\"all\">\n"
                + "</beans>"), "beans.xml");
    }

    @Inject
    private IQuoteService service;

    @Test
    public void testService() {
        Path dumpPath = Path.of("src","test", "resources", "dump", "euraud.json");
        log.info("dumpPath found "+dumpPath.toFile().exists());
        try (InputStream is = new FileInputStream(dumpPath.toFile())){
            int result = service.importToDb(is);
            assertEquals(1, result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<ReadQuoteResponse> result = service.readQuotes(1533723600197999999l,1533723600198000001l,null);
        assertEquals(1, result.size());
    }
}
