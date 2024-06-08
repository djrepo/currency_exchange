package com.shorturl.repository;

import com.shorturl.model.Quote;
import com.shorturl.model.UrlEntity;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.jooq.DSLContext;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository class for managing URL entities.
 */
@ApplicationScoped
public class QuoteRepository implements IQuoteRepository {

    private static final String FIND_BY_ID = "SELECT u FROM Quote u WHERE u.id = :id";
    private static final String FIND_BY_HASH = "SELECT u FROM Quote u WHERE u.originalUrlHash = :hash and u.originalUrl = :originalUrl";

    @Resource(lookup = "java:/jdbc/MariaDS")
    private DataSource dataSource;
    @Inject
    private DSLContext ctx;


    @Override
    public void storeAll(List<Quote> quotes) {
        AuthorRecord authorRecord = ctx.insertInto(AUTHOR, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .values(object.getString("author_firstname"), object.getString("author_lastname"))
                .returning()
                .fetchOne();

        QueryRunner queryRunner = new QueryRunner(dataSource.getConnection());
        queryRunner.insertBatch("insert into QUOTE () values (??)", new ResultSetHandler<Quote>() {
            @Override
            public Quote handle(ResultSet resultSet) throws SQLException {
                return null;
            }
        },null);
        for (Quote quote : quotes) {
            em.persist(quote);
        }
    }

    @Override
    public Quote findById(long id) {
        return em.createQuery(FIND_BY_ID, Quote.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Quote findByHashAndOriginalUrl(Quote urlEntity) {
        return em.createQuery(FIND_BY_HASH, Quote.class)
                .setParameter("hash", urlEntity.getOriginalUrlHash())
                .setParameter("originalUrl", urlEntity.getOriginalUrl())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
