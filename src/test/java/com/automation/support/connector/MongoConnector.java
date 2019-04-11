package com.automation.support.connector;

import com.automation.support.Configuration;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.jongo.Jongo;
import org.jongo.marshall.jackson.JacksonMapper;

import java.net.UnknownHostException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class MongoConnector {

    private Configuration configuration = Configuration.get();
    private MongoClient mongoClient;
    private Jongo jongo;
    private static MongoConnector _instance;

    String host;
    String port;
    String database;
    String user;
    String password;

    public MongoConnector(){

        mongoClient = getMongoClient();
    }

    public static MongoConnector get() {
        if (_instance == null) {
            _instance = new MongoConnector();
        }
        return _instance;
    }

    public Jongo getJongo() {

        if (jongo == null) {
            jongo = new Jongo(getMongoClient().getDB(database), new JacksonMapper.Builder()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    // .registerModule(new BsonJodaTimeModule())
                    .build());
        }

        assertNotNull(jongo);
        return jongo;
    }

    private MongoClient getMongoClient() {

        if (mongoClient == null) {
            System.out.println("Setting up mongoClient");

            host = configuration.getTestProperty("mongo.host");
            port = configuration.getTestProperty("mongo.port");
            database = configuration.getTestProperty("mongo.database");
            user = configuration.getTestProperty("mongo.user");
            password = configuration.getTestProperty("mongo.password");

            MongoCredential credential = MongoCredential.createMongoCRCredential(user, database, password.toCharArray());
            try {
                ServerAddress serverAddress = new ServerAddress(host, Integer.valueOf(port));
                mongoClient = new MongoClient(Arrays.asList(serverAddress), Arrays.asList(credential));
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Failed to connect to mongodb");
            }

        }
        return mongoClient;
    }

}
