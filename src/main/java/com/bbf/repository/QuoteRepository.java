package com.bbf.repository;

import com.bbf.model.QuoteEntity;
import com.bbf.model.mongo.Quote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



import java.util.List;

/**
 * Repository class for managing URL entities.
 */
@ApplicationScoped
public class QuoteRepository implements IQuoteRepository {

    private static final String FIND_BY_BOUNDARIES = "SELECT u FROM QuoteEntity u WHERE u.time > :from and u.time < :to";

    private static final String FIND_BY_BOUNDARIES_AND_TYPE_ASK = "SELECT u FROM QuoteEntity u WHERE u.time > :from and u.time < :to and u.ask > 0";

    private static final String FIND_BY_BOUNDARIES_AND_TYPE_BID = "SELECT u FROM QuoteEntity u WHERE u.time > :from and u.time < :to and u.bid > 0";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void storeAll(List<QuoteEntity> quotes) {
        for (QuoteEntity quote : quotes) {
            entityManager.persist(quote);
        }
    }

    @Override
    public List<QuoteEntity> findByBoundaries(long from, long to) {
        return entityManager.createQuery(FIND_BY_BOUNDARIES, QuoteEntity.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
    @Override
    public List<QuoteEntity> findByBoundariesWithAskPositive(long from, long to) {
        return entityManager.createQuery(FIND_BY_BOUNDARIES_AND_TYPE_ASK, QuoteEntity.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public List<QuoteEntity> findByBoundariesWithBidPositive(long from, long to) {
        return entityManager.createQuery(FIND_BY_BOUNDARIES_AND_TYPE_BID, QuoteEntity.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }


}
