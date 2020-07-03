package com.javatest;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {

    private static final Logger LOG = LoggerFactory.getLogger(MongoTest.class);


    public static void main(String []args) {


        List<ServerAddress> serverAddrs = new ArrayList<>();
        serverAddrs.add(new ServerAddress("10.131.20.16", 8991));
        //serverAddrs.add(new ServerAddress("10.131.20.17", 8991));
        //serverAddrs.add(new ServerAddress("10.131.20.18", 8991));


        MongoCredential credential = MongoCredential.createCredential("root", "admin", "root".toCharArray());

        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);

        MongoClient client = new MongoClient(serverAddrs, credentials);


        // MongoClient client = new MongoClient(serverAddrs);

        MongoDatabase mongodb = client.getDatabase("odltest");

        // mongodb.createCollection("testconllection");

        MongoCollection<Document> collection = mongodb.getCollection("testconllection");

        Document doc = new Document().append("hello", "world").append("hello2", "world2").append("git", "hook");
        collection.insertOne(doc);

        doc = new Document().append("11", "22").append("231", "666").append("abc", "esf");
        collection.insertOne(doc);


    }

}

