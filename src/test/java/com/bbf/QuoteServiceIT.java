package com.bbf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bbf.config.JaxRsActivator;
import com.bbf.controller.QuoteResource;
import com.bbf.model.QuoteEntity;
import com.bbf.repository.QuoteRepository;
import com.bbf.service.IQuoteService;
import com.bbf.service.QuoteService;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Run integration tests with Arquillian to be able to test CDI beans
 */
@ExtendWith(ArquillianExtension.class)
//@CleanupUsingScript("clean-database.sql")
//@UsingDataSet("init-database.xml")
public class QuoteServiceIT {

    @Deployment
    public static WebArchive createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(QuoteEntity.class.getPackage())
                .addClasses(QuoteService.class, QuoteResource.class, QuoteRepository.class,JaxRsActivator.class)
                .addAsResource("META-INF/persistence-h2.xml")
                //.addAsWebInfResource("arquillian-ds.xml")
                .addAsResource("META-INF/microprofile-config.properties")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private IQuoteService service;

    @Test
    public void testService() {
        Path dumpPath = Path.of("test", "resources", "dump", "euraud.json");
        try (InputStream is = new FileInputStream(dumpPath.toFile())){
            int result = service.importToDb(is);
            assertEquals(1, result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<QuoteEntity> result = service.readQuotes(1533723600197999999l,1533723600198000001l,null);
        assertEquals(1, result.size());
    }
}
