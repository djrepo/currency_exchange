package com.shorturl.config;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

@ApplicationScoped
public class JooqDslContextProducer {

    @Resource(lookup="java:/jdbc/MariaDS")
    DataSource dataSource;

    @Produces
    public DSLContext jooq() {
        return DSL.using(dataSource, SQLDialect.MARIADB);
    }


}
