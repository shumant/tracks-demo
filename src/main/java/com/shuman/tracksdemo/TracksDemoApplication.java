package com.shuman.tracksdemo;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TracksDemoApplication {

    public static void main(String[] args) throws Exception {

        try (EmbeddedPostgres pg = EmbeddedPostgres.builder().start()) {
            SpringApplication.run(TracksDemoApplication.class, args);
        }
    }
}
