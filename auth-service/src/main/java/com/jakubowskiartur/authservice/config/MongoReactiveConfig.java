//package com.jakubowskiartur.authservice.config;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//@EnableReactiveMongoRepositories
//public class MongoReactiveConfig extends AbstractReactiveMongoConfiguration {
//
//    @Override
//    public @Bean MongoClient reactiveMongoClient() {
//        return MongoClients.create("mongodb://authdb");
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "authdb";
//    }
//
//
//}
