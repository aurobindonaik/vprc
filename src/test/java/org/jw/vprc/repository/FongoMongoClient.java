package org.jw.vprc.repository;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
public class FongoMongoClient {
    /**
     * Modern MongoDB tools need MongoClient
     * @return
     */
    @Bean
    public MongoClient mongoClient() {
        return new Fongo("vprc-mongo-server").getMongo();
    }
}
