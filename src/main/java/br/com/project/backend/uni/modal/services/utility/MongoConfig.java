package br.com.project.backend.uni.modal.services.utility;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://localhost:27017");

    }
    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient){
        return mongoClient.getDatabase("mongoBackendUni");
    }

    @Bean
    public GridFSBucket gridFSBucket(MongoDatabase mongoDatabase){
        return GridFSBuckets.create(mongoDatabase);
    }

}
