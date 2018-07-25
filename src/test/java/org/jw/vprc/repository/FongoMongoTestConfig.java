package org.jw.vprc.repository;

import com.github.fakemongo.Fongo;
import com.lordofthejars.nosqlunit.mongodb.MongoDbConfiguration;
import com.lordofthejars.nosqlunit.mongodb.SpringMongoDbRule;
import com.mongodb.MockMongoClient;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@TestConfiguration
@EnableMongoRepositories
@Import(FongoMongoClient.class)
public class FongoMongoTestConfig extends AbstractMongoConfiguration {
    public static final String VPRC_TEST_MONGODB = "vprc-test-mongodb";

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoProperties mongoProperties;

    public static SpringMongoDbRule getSpringFongoMongoDbRule() {
        MongoDbConfiguration mongoDbConfiguration = new MongoDbConfiguration();
        mongoDbConfiguration.setDatabaseName(VPRC_TEST_MONGODB);
        MongoClient mongo = MockMongoClient.create(new Fongo("this-fongo-instance-is-ignored"));
        mongoDbConfiguration.setMongo(mongo);
        return new SpringMongoDbRule(mongoDbConfiguration);
    }

    @Bean
    public Mongo mongo() {
        mongoProperties.setDatabase(VPRC_TEST_MONGODB);
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return VPRC_TEST_MONGODB;
    }

}

