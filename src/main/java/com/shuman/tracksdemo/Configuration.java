package com.shuman.tracksdemo;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
public class Configuration {
    private EmbeddedPostgres embeddedPostgres;

    @Bean
    public DataSource dataSource() throws Exception {
        embeddedPostgres = EmbeddedPostgres.start();
        return embeddedPostgres.getPostgresDatabase();
    }

    @PreDestroy
    public void destroy() throws Exception {
        embeddedPostgres.close();
    }

}
